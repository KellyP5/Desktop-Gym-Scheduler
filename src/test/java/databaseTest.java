import main.java.memoranda.database.*;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.database.util.SqlConstants;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

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

    @Test
    public void testgetAllClassesByDate_classData1(){

    }

    /**
     * Pupulates testdb with test classes
     *
     *
     *
     */



    /*

        The following are database creation methods for testing
        the database.

     */

    public void classData1(){
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.black3);

        BeltEntity minBelt = new BeltEntity(BeltEntity.Rank.white);

        UserEntity trainer = new UserEntity("kevin",
                "kevin","kevin",
                "kevin@kevin.com",
                re,
                be,
                be);



        LocalDate localDate = LocalDate.of(2020,4,11);
        LocalTime lst = LocalTime.of(12,30);
        LocalTime let = LocalTime.of(13,30);
        LocalDateTime localStartDateTime = LocalDateTime.of(localDate,lst);
        LocalDateTime localEndDateTime = LocalDateTime.of(localDate, localEndTime);

        GymClassEntity class1 = new GymClassEntity(1,
                1,
                localStartDateTime,
                localEndDateTime,
                trainer.getEmail(),
                20,
                minBelt,
                trainer.getEmail());

        dcq.insertClass(1);
    }

    public void addCustomerData_5Whites() throws SQLException {
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.customer);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.white);

        ArrayList<UserEntity> customers = new ArrayList<UserEntity>();
        for(int i = 0;i< 5;i++){
            UserEntity ue = new UserEntity("white"+i,
                    "white"+i,"white"+i,
                    "white@white.com"+i,
                    re,
                    be,
                    be);

            dcq.insertUser(ue.getEmail(),
                    ue.getFirstName(),
                    ue.getLastName(),
                    ue.getPassword(),
                    ue.getRole(),
                    ue.getBelt(),
                    ue.getTrainingBelt());
        }
    }

    public void addCustomerData_5GreenStripes() throws SQLException {
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.customer);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.green_stripe);

        ArrayList<UserEntity> customers = new ArrayList<UserEntity>();
        for(int i = 0;i< 5;i++){
            UserEntity ue = new UserEntity("greenStripe"+i,
                    "greenStripe"+i,"greenStripe"+i,
                    "greenStripe@greenStripe.com"+i,
                    re,
                    be,
                    be);

            dcq.insertUser(ue.getEmail(),
                    ue.getFirstName(),
                    ue.getLastName(),
                    ue.getPassword(),
                    ue.getRole(),
                    ue.getBelt(),
                    ue.getTrainingBelt());
        }
    }

    public void addCustomerData_5black3() throws SQLException {
        RoleEntity re = new RoleEntity(RoleEntity.UserRole.customer);
        BeltEntity be = new BeltEntity(BeltEntity.Rank.black3);

        ArrayList<UserEntity> customers = new ArrayList<UserEntity>();
        for(int i = 0;i< 5;i++){
            UserEntity ue = new UserEntity("black3"+i,
                    "black3"+i,"black3"+i,
                    "black3@black3.com"+i,
                    re,
                    be,
                    be);

            dcq.insertUser(ue.getEmail(),
                    ue.getFirstName(),
                    ue.getLastName(),
                    ue.getPassword(),
                    ue.getRole(),
                    ue.getBelt(),
                    ue.getTrainingBelt());
        }
    }
}
