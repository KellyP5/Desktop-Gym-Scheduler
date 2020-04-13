//import main.java.memoranda.database.*;
//import main.java.memoranda.database.entities.BeltEntity;
//import main.java.memoranda.database.entities.GymClassEntity;
//import main.java.memoranda.database.entities.RoleEntity;
//import main.java.memoranda.database.entities.UserEntity;
//import main.java.memoranda.database.querries.DbCreateQueries;
//import main.java.memoranda.database.querries.DbReadQueries;
//import main.java.memoranda.database.util.SqlConstants;
//import org.junit.*;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class databaseTest {
//    public static SqlConnection sqlConnection = null;
//    public static DbCreateQueries dcq = null;
//    public static DbReadQueries drq = null;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        sqlConnection = SqlConnection.getInstance();
//        dcq = sqlConnection.getDcqTest();
//        drq = sqlConnection.getDrqTest();
//        sqlConnection.getDbSetupHelperTest().deleteTestTables();
//        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
//    }
//
//    @AfterClass
//    public static void tearDown() throws Exception {
//        sqlConnection.getDbSetupHelperTest().closeDatabase();
//    }
//
//    @Test
//    public void userInsertedThenRetrievedFromDbIsEqual() throws SQLException {
//        RoleEntity trainer = new RoleEntity(RoleEntity.UserRole.trainer);
//        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
//        UserEntity userOriginal = new UserEntity("kevin",
//                "johnson","foo","kevin@gmail.com",trainer,white,white);
//        dcq.insertUser(userOriginal.getEmail(),
//                userOriginal.getFirstName(),
//                userOriginal.getLastName(),
//                userOriginal.getPassword(),
//                userOriginal.getRole(),
//                userOriginal.getBelt(),
//                userOriginal.getTrainingBelt());
//        UserEntity userFromDb = drq.getUserByEmail(userOriginal.getEmail());
//
//        assertEquals(userOriginal, userFromDb);
//    }
//
//    @Test
//    public void gymClassInsertedThenRetrievedFromDbIsEqual() throws SQLException {
//        //create a UserEntity trainer and admin that we can use for creating the gymclass
//        RoleEntity trainerRole = new RoleEntity(RoleEntity.UserRole.trainer);
//        RoleEntity adminRole = new RoleEntity(RoleEntity.UserRole.admin);
//        BeltEntity white = new BeltEntity(BeltEntity.Rank.white);
//        UserEntity trainerUser = new UserEntity("steve",
//                "jones","foo","steve@gmail.com",trainerRole,white,
//                white);
//        UserEntity adminUser = new UserEntity("susan","baker",
//                "foobar","susan@gmail.com",adminRole, white, white);
//        dcq.insertUser(trainerUser.getEmail(),
//                trainerUser.getFirstName(),
//                trainerUser.getLastName(),
//                trainerUser.getPassword(),
//                trainerUser.getRole(),
//                trainerUser.getBelt(),
//                trainerUser.getTrainingBelt());
//        dcq.insertUser(adminUser.getEmail(),
//                adminUser.getFirstName(),
//                adminUser.getLastName(),
//                adminUser.getPassword(),
//                adminUser.getRole(),
//                adminUser.getBelt(),
//                adminUser.getTrainingBelt());
//
//        //create all objects necessary for creating a gym class
//        BeltEntity minReqBeltIsGreen = new BeltEntity(BeltEntity.Rank.green);
//        LocalDate localDate = LocalDate.of(2020,4,11);
//        LocalTime localStartTime = LocalTime.of(12,30);
//        LocalTime localEndTime = LocalTime.of(13,30);
//        LocalDateTime localStartDateTime = LocalDateTime.of(localDate,localStartTime);
//        LocalDateTime localEndDateTime = LocalDateTime.of(localDate, localEndTime);
//
//        GymClassEntity classOriginal = new GymClassEntity(1,
//                1,
//                localStartDateTime,
//                localEndDateTime,
//                trainerUser.getEmail(),
//                20,
//                minReqBeltIsGreen,
//                adminUser.getEmail());
//
//        double startTimeAsDouble = Double.parseDouble(localStartDateTime.toLocalTime().
//                format(SqlConstants.DBTIMEFORMAT));
//        double endTimeAsDouble = Double.parseDouble(localEndDateTime.toLocalTime().
//                format(SqlConstants.DBTIMEFORMAT));
//
//        dcq.insertClass(classOriginal.getId(),
//                classOriginal.getRoomNumber(),
//                classOriginal.getStartDateTime().format(SqlConstants.DBDATEFORMAT),
//                startTimeAsDouble,
//                endTimeAsDouble,
//                trainerUser.getEmail(),
//                20,
//                minReqBeltIsGreen,
//                adminUser.getEmail());
//        ArrayList<GymClassEntity> classes = drq.getAllClassesByDate(localDate);
//        assertEquals(classes.get(0), classOriginal);
//    }
//
//
//
//
//
//}
