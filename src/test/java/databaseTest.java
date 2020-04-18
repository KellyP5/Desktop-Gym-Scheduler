package test.java;

import main.java.memoranda.database.*;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.database.util.DbUpdateQueries;
import main.java.memoranda.database.util.SqlConstants;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class databaseTest {
    public static SqlConnection sqlConnection = null;
    public static DbCreateQueries dcq = null;
    public static DbReadQueries drq = null;
    public static DbUpdateQueries duq = null;

    @BeforeClass
    public static void setUp() throws Exception {
        sqlConnection = SqlConnection.getInstance();
        dcq = sqlConnection.getDcqTest();
        drq = sqlConnection.getDrqTest();
        duq = sqlConnection.getDuqTest();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        sqlConnection.getDbSetupHelperTest().closeDatabase();

        dcq.deleteUser("Customer@gmail.com");
        dcq.deleteUser("Trainer@gmail.com");
    }

    @Test
    public void userInsertedThenRetrievedFromDbIsEqual() throws SQLException {
        RoleEntity trainer = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
        UserEntity userOriginal = new UserEntity("kevin",
                "johnson","foo","kevin@gmail.com",trainer,white,white);
        dcq.insertUser(userOriginal.getEmail(),
                userOriginal.getFirstName(),
                userOriginal.getLastName(),
                userOriginal.getPassword(),
                userOriginal.getRole(),
                userOriginal.getBelt(),
                userOriginal.getTrainingBelt());
        UserEntity userFromDb = drq.getUserByEmail(userOriginal.getEmail());

        assertEquals(userOriginal, userFromDb);
    }

    @Test
    public void gymClassInsertedThenRetrievedFromDbIsEqual() throws SQLException {
        //create a UserEntity trainer and admin that we can use for creating the gymclass
        RoleEntity trainerRole = new RoleEntity(RoleEntity.UserRole.trainer);
        RoleEntity adminRole = new RoleEntity(RoleEntity.UserRole.admin);
        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
        UserEntity trainerUser = new UserEntity("steve",
                "jones","foo","steve@gmail.com",trainerRole,white,
                white);
        UserEntity adminUser = new UserEntity("susan","baker",
                "foobar","susan@gmail.com",adminRole, white, white);
        dcq.insertUser(trainerUser.getEmail(),
                trainerUser.getFirstName(),
                trainerUser.getLastName(),
                trainerUser.getPassword(),
                trainerUser.getRole(),
                trainerUser.getBelt(),
                trainerUser.getTrainingBelt());
        dcq.insertUser(adminUser.getEmail(),
                adminUser.getFirstName(),
                adminUser.getLastName(),
                adminUser.getPassword(),
                adminUser.getRole(),
                adminUser.getBelt(),
                adminUser.getTrainingBelt());

        //create all objects necessary for creating a gym class
        BeltEntity minReqBeltIsGreen = new BeltEntity(BeltEntity.Rank.green);
        LocalDate localDate = LocalDate.of(2020,4,11);
        LocalTime localStartTime = LocalTime.of(12,30);
        LocalTime localEndTime = LocalTime.of(13,30);
        LocalDateTime localStartDateTime = LocalDateTime.of(localDate,localStartTime);
        LocalDateTime localEndDateTime = LocalDateTime.of(localDate, localEndTime);

        GymClassEntity classOriginal = new GymClassEntity(1,
                1,
                localStartDateTime,
                localEndDateTime,
                trainerUser.getEmail(),
                20,
                minReqBeltIsGreen,
                adminUser.getEmail());

        double startTimeAsDouble = Double.parseDouble(localStartDateTime.toLocalTime().
                format(SqlConstants.DBTIMEFORMAT));
        double endTimeAsDouble = Double.parseDouble(localEndDateTime.toLocalTime().
                format(SqlConstants.DBTIMEFORMAT));

        dcq.insertClass(classOriginal.getId(),
                classOriginal.getRoomNumber(),
                classOriginal.getStartDateTime().format(SqlConstants.DBDATEFORMAT),
                startTimeAsDouble,
                endTimeAsDouble,
                trainerUser.getEmail(),
                20,
                minReqBeltIsGreen,
                adminUser.getEmail());
        ArrayList<GymClassEntity> classes = drq.getAllClassesByDate(localDate);
        assertEquals(classes.get(0), classOriginal);
    }


    @Test
    public void deleteUser() throws SQLException {
        RoleEntity trainer = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
        UserEntity userOriginal = new UserEntity("kevin",
                "johnson","foo","ash@gmail.com",trainer,white,white);
        dcq.insertUser(userOriginal.getEmail(),
                userOriginal.getFirstName(),
                userOriginal.getLastName(),
                userOriginal.getPassword(),
                userOriginal.getRole(),
                userOriginal.getBelt(),
                userOriginal.getTrainingBelt());
        dcq.deleteUser(userOriginal.getEmail());
        UserEntity userFromDb = drq.getUserByEmail(userOriginal.getEmail());
        assertNull(userFromDb);
    }

    @Test
    public void customerUserIsUpdated() throws SQLException {
        RoleEntity role = new RoleEntity(RoleEntity.UserRole.customer);
        BeltEntity belt = new BeltEntity(BeltEntity.Rank.orange);
        UserEntity customer = new UserEntity("CustomerFirst", "CustomerLast", "pass", "Customer@gmail.com", role);
        dcq.insertUser(customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPassword(), customer.getRole());

        duq.updateUser("Customer@gmail.com", "Kelly", "Last", "pass", role, belt);

        UserEntity newUser = drq.getUserByEmail("Customer@gmail.com");

        assertTrue(newUser.getFirstName().equals("Kelly"));
    }

    @Test
    public void trainerUserIsUpdated() throws SQLException {
        RoleEntity role = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity belt = new BeltEntity(BeltEntity.Rank.orange);
        BeltEntity trainBelt = new BeltEntity(BeltEntity.Rank.yellow);
        UserEntity trainer = new UserEntity("TrainerFirst", "TrainerLast", "test", "Trainer@gmail.com", role);
        dcq.insertUser(trainer.getEmail(), trainer.getFirstName(), trainer.getLastName(), trainer.getPassword(), role, belt, trainBelt);

        duq.updateUser("Trainer@gmail.com", "Kelly", "Last", "test", role, belt);

        UserEntity newUser1 = drq.getUserByEmail("Trainer@gmail.com");

        assertTrue(newUser1.getLastName().equals("Last"));
    }
}
