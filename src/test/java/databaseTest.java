package test.java;

import main.java.memoranda.database.*;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.database.util.DbUpdateQueries;
import main.java.memoranda.database.util.SqlConstants;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.*;

import java.sql.SQLException;
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

    @After
    public void tearDown() throws Exception {
        //sqlConnection.getDbSetupHelperTest().closeDatabase();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
        SqlConnection.close();

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
    public void testNull_emptyDb_drq_getUserByEmail() throws SQLException{
        UserEntity ue = drq.getUserByEmail("IDONTEXISTS@yourmoma.com");
        assertEquals(null,ue);
    }

    @Test
    public void testNotNull_nonEmptyDb_drq_getUserByEmail() throws SQLException{
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.admin);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.black3);

        UserEntity ue1 = new UserEntity("kevin",
                "kevin","kevin",
                "kevin@kevin.com",
                re,
                be,
                be);

        dcq.insertUser(ue1.getEmail(),
                ue1.getFirstName(),
                ue1.getLastName(),
                ue1.getPassword(),
                ue1.getRole(),
                ue1.getBelt(),
                ue1.getTrainingBelt());

        UserEntity expected = ue1;
        UserEntity testEntity = drq.getUserByEmail("kevin@kevin.com");

        assertEquals(expected,testEntity);


    }

    @Test
    public void test1000Inserts_drq() throws SQLException{
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.admin);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.black3);

        UserEntity expected = new UserEntity("kevin",
                "kevin","kevin",
                "kevin@kevin.com",
                re,
                be,
                be);


        for(int i = 0;i< 1000;i++){
            UserEntity ue1 = new UserEntity("kevin",
                    "kevin","kevin",
                    "kevin@kevin.com"+i,
                    re,
                    be,
                    be);

            dcq.insertUser(ue1.getEmail(),
                    ue1.getFirstName(),
                    ue1.getLastName(),
                    ue1.getPassword(),
                    ue1.getRole(),
                    ue1.getBelt(),
                    ue1.getTrainingBelt());
        }

        ArrayList<UserEntity> ues = drq.getAllUsers();

        assertEquals(1000,ues.size());
        assertEquals(expected.getEmail()+499,ues.get(499).getEmail());
    }

    @Test
    public void test0Inserts_drq() throws SQLException{
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.admin);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.black3);

        ArrayList<UserEntity> ues = drq.getAllUsers();

        assertEquals(0,ues.size());
    }

    @Test(expected = SQLException.class)
    public void testCreatedByReference() throws SQLException {

        BeltEntity minBelt = new BeltEntity(BeltEntity.Rank.white);
        dcq.insertClass(
                1,
                "04/28/2020",
                12.0,
                13.0,
                "kevin@kevin.com",
                20,
                minBelt,
                "kevin@kevin.com");
    }

    @Test
    public void gettingAllClassesBySpecificTrainerReturnsAllClassesExpected() throws SQLException {
        //create some necessary entities to be able to make gym classes
        RoleEntity trainerRole = new RoleEntity(RoleEntity.UserRole.trainer);
        RoleEntity adminRole = new RoleEntity(RoleEntity.UserRole.admin);
        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
        ArrayList<UserEntity> usersToAdd = new ArrayList<>();
        usersToAdd.add(new UserEntity("jack",
                "johnson","foobar","jackj@gmail.com",trainerRole,white,
                white));
        usersToAdd.add(new UserEntity("jack",
                "ryans","foo","jack@gmail.com",adminRole,white,
                white));
        for (UserEntity user: usersToAdd) {
            dcq.insertUser(user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    user.getRole(),
                    user.getBelt(),
                    user.getTrainingBelt());
        }
        BeltEntity minReqBeltIsGreen = new BeltEntity(BeltEntity.Rank.green);
        LocalTime localStartTime = LocalTime.of(12,30);
        LocalTime localEndTime = LocalTime.of(13,30);

        ArrayList<GymClassEntity> gymClassesToAdd = new ArrayList<>();

        //we'll create 4 gymclasses (no reason, just chose 4), to be added to the database with jackj@gmail.com as
        // the trainer
        for(int i = 1; i < 5; i++){
            LocalDate localDate = LocalDate.of(2020,5,i);
            LocalDateTime localStartDateTime = LocalDateTime.of(localDate,localStartTime);
            LocalDateTime localEndDateTime = LocalDateTime.of(localDate, localEndTime);

            gymClassesToAdd.add(new GymClassEntity(
                    i,
                    i,
                    localStartDateTime,
                    localEndDateTime,
                    "jackj@gmail.com",
                    20,
                    minReqBeltIsGreen,
                    "jack@gmail.com")
            );
        }
        //create 1 gymclass to be added to the database with jack@gmail.com as the trainer and created by
        LocalDate localDate = LocalDate.of(2020,5,5);
        gymClassesToAdd.add(new GymClassEntity(5,
                1, LocalDateTime.of(localDate, localStartTime),LocalDateTime.of(localDate, localEndTime),
                "jack@gmail.com",20, white,"jack@gmail.com"));

        LocalDateTime localStartDateTime = LocalDateTime.of(localDate,localStartTime);
        LocalDateTime localEndDateTime = LocalDateTime.of(localDate, localEndTime);

        //insert all the classes trained by both jackj@gmail.com and jack@gmail.com
        for(GymClassEntity gymClass: gymClassesToAdd){
            double startTimeAsDouble = Double.parseDouble(localStartDateTime.toLocalTime().
                    format(SqlConstants.DBTIMEFORMAT));
            double endTimeAsDouble = Double.parseDouble(localEndDateTime.toLocalTime().
                    format(SqlConstants.DBTIMEFORMAT));

            dcq.insertClass(
                    gymClass.getRoomNumber(),
                    gymClass.getStartDateTime().format(SqlConstants.DBDATEFORMAT),
                    startTimeAsDouble,
                    endTimeAsDouble,
                    gymClass.getTrainerEmail(),
                    gymClass.getMaxClassSize(),
                    gymClass.getMinBeltEntityRequired(),
                    gymClass.getCreatedByEmail()
                    );
        }

        //assert all expected values to be returned
        ArrayList<GymClassEntity> classesByJackJ = drq.getAllClassesTrainerIsTeachingByEmail("jAcKJ@gmail.com");
        assertEquals(classesByJackJ.size(), 4);
        for (GymClassEntity gymClass: classesByJackJ) {
            assertEquals(gymClass.getTrainerEmail(), "jackj@gmail.com");
        }
        ArrayList<GymClassEntity> classesByJack = drq.getAllClassesTrainerIsTeachingByEmail("jAcK@gmail.com");
        assertEquals(classesByJack.size(), 1);
        assertEquals(classesByJack.get(0).getTrainerEmail(), "jack@gmail.com");
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

    @Test(expected = SQLException.class)
    public void addGymClassReferringToNonExistantTrainerFails() throws SQLException {
        BeltEntity minReqBeltIsGreen = new BeltEntity(BeltEntity.Rank.green);
        //now we attempt to add a class that references a trainer and admin that do not exist, which should throw
        //an exception
        dcq.insertClass(1,
                1,
                "04/18/2020",
                11.32,
                12.59,
                "sdflksjdfl@yahoo.com",
                20,
                minReqBeltIsGreen,
                "1234234234@gmail.com");

    }
}
