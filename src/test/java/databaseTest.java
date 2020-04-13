import main.java.memoranda.database.*;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.database.util.SqlConstants;
import org.junit.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class databaseTest {
    public static SqlConnection sqlConnection = null;
    public static DbCreateQueries dcq = null;
    public static DbReadQueries drq = null;

    @BeforeClass
    public static void setUp() throws Exception {
        sqlConnection = SqlConnection.getInstance();
        dcq = sqlConnection.getDcqTest();
        drq = sqlConnection.getDrqTest();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        sqlConnection.getDbSetupHelperTest().closeDatabase();
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





}
