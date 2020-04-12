package main.java.memoranda.database.util;

import main.java.memoranda.database.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/*
Utility class with many helpful methods for setup and configuration of the real db and test db
 */
public class DbSetupHelper {

    public static void main(String[] args) throws SQLException {
        //create primary db
        DbSetupHelper dbSetupHelper = new DbSetupHelper();
        dbSetupHelper.createDatabase();
        dbSetupHelper.createNeujahrskranzTables();
        //create test db
        dbSetupHelper.setDbUrl(SqlConstants.DEFAULTTESTDBLOC);
        dbSetupHelper.createDatabase();
        dbSetupHelper.createNeujahrskranzTables();
        //add sample data to test
        dbSetupHelper.addSampleDataToDb(SqlConstants.DEFAULTTESTDBLOC);

    }

    private String _dbUrl;

    public DbSetupHelper() {
        this._dbUrl = SqlConstants.DEFAULTDBLOC;
    }
    public DbSetupHelper(String _dbUrl) {
        this._dbUrl = _dbUrl;
    }
    public String getDbUrl() {
        return _dbUrl;
    }
    public void setDbUrl(String _dbURL) {
        this._dbUrl = _dbURL;
    }

    /*
    creates an sqlite database at dbURL
     */
    public void createDatabase(){
        try (Connection conn = DriverManager.getConnection(_dbUrl)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    closes the database at dbURL
     */
    public void closeDatabase(){
        try {
            Connection conn = DriverManager.getConnection(_dbUrl);
            conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("failed to close!");
        }
    }
    /*
    creates a table at dbURL using the sql string passed in.  The tableName string is just used for
    a nice print out of what the method is doing.
     */
    public void createTable(String sql, String tableName){
        try (Connection conn = DriverManager.getConnection(_dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table: " + tableName + " was created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    creates tables specific to the Neujahskranz project
     */
    public void createNeujahrskranzTables(){
        createDatabase();
        System.out.println("Preparing to create tables..note that if the tables already exist " +
                "they will not be created");
        //create USER
        String userSql = "CREATE TABLE IF NOT EXISTS USER (\n"
                + "    Email text PRIMARY KEY NOT NULL,\n"
                + "    FirstName text NOT NULL,\n"
                + "    LastName text NOT NULL,\n"
                + "    Password text NOT NULL,\n"
                + "    Role text NOT NULL,\n"
                + "    Belt text,\n"
                + "    TrainingBelt text\n"
                + ");";
        createTable(userSql, "User");

        //create GYMCLASS
        String classSql = "CREATE TABLE IF NOT EXISTS GYMCLASS (\n"
                + "    Id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "    RoomNumber integer NOT NULL,\n"
                + "    StartDate text NOT NULL,\n"
                + "    StartTime real NOT NULL,\n"
                + "    EndTime real NOT NULL,\n"
                + "    TrainerEmail text NOT NULL,\n"
                + "    MaxClassSize integer NOT NULL,\n"
                + "    MinBeltRequired text NOT NULL,\n"
                + "    CreatedByEmail text NOT NULL,\n"
                + "    FOREIGN KEY(TrainerEmail) REFERENCES USER(Email) ON DELETE CASCADE,\n"
                + "    FOREIGN KEY(CreatedByEmail) REFERENCES USER(Email) ON DELETE CASCADE\n"
                + ");";
        createTable(classSql, "GymClass");

        //create TRAINERAVAILABILITY
        String trainerAvailSql = "CREATE TABLE IF NOT EXISTS TRAINERAVAILABILITY (\n"
                + "    TrainerEmail text NOT NULL,\n"
                + "    StartDate text NOT NULL,\n"
                + "    StartTime real NOT NULL,\n"
                + "    EndTime real NOT NULL,\n"
                + "    PRIMARY KEY(TrainerEmail, StartDate, StartTime, EndTime),\n"
                + "    FOREIGN KEY(TrainerEmail) REFERENCES USER(Email) ON DELETE CASCADE\n"
                + ");";
        createTable(trainerAvailSql, "TrainerAvailability");

        //create ENROLLEDUSER
        String enrolledUsersSql = "CREATE TABLE IF NOT EXISTS ENROLLEDUSER (\n"
                + "    ClassId integer NOT NULL,\n"
                + "    UserEmail text NOT NULL,\n"
                + "    PRIMARY KEY(ClassId, UserEmail),\n"
                + "    FOREIGN KEY(UserEmail) REFERENCES USER(Email) ON DELETE CASCADE\n"
                + "    FOREIGN KEY(ClassId) REFERENCES GYMCLASS(Id) ON DELETE CASCADE\n"
                + ");";
        createTable(enrolledUsersSql, "EnrolledUser");
    }
    /*
    adds sample data to the database at databaseURL, this method is tightly coupled with the create
    table method and has very specific expecations about what tables exist.
     */
    public void addSampleDataToDb(String databaseUrl) throws SQLException {
        DbCreateQueries dcq = new DbCreateQueries(databaseUrl);
        RoleEntity customer = new RoleEntity(RoleEntity.UserRole.customer);
        RoleEntity admin = new RoleEntity(RoleEntity.UserRole.admin);
        RoleEntity trainer = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity minRequiredBelt = new BeltEntity(BeltEntity.Rank.white);
        BeltEntity blackBelt = new BeltEntity(BeltEntity.Rank.black1);
        dcq.insertUser("kevin@gmail.com","kevin","johnson",
                "foo", customer);
        dcq.insertUser("steve@gmail.com", "steve", "jacobs",
                "foobar", admin);
        dcq.insertUser("sarah@gmail.com", "sarah", "baker",
                "abc123", trainer);
        dcq.insertUser("brenda@gmail.com", "brenda", "wiley",
                "sdfsdf", admin, blackBelt, blackBelt);
        dcq.insertClass(1,"04/11/2020",12.5,13.0,
                "sarah@gmail.com",
                20,minRequiredBelt,"steve@gmail.com");
        dcq.insertClass(2,"04/12/2020",14.0,15.5,
                "sarah@gmail.com",
                20,minRequiredBelt,"steve@gmail.com");
        dcq.insertClass(3,"04/11/2020",8.0,9.0,
                "sarah@gmail.com",
                20,minRequiredBelt,"steve@gmail.com");

        dcq.insertTrainerAvailability("sarah@gmail.com", "04/12/2020",
                10.0,14.0);
        dcq.insertTrainerAvailability("sarah@gmail.com", "04/12/2020",
                15.0,18.0);
        dcq.insertEnrolledUser(1, "kevin@gmail.com");
        dcq.insertEnrolledUser(1, "brenda@gmail.com");
        dcq.insertEnrolledUser(2, "kevin@gmail.com");
    }

    private void _testAndPrintDataFromDb(String databaseUrl) throws SQLException {
        DbReadQueries drq = new DbReadQueries(databaseUrl);

        UserEntity user = null;
        try {
            user = drq.getUserByEmail("kevin@gmail.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("first name is: " + user.getFirstName());
        System.out.println("belt is: " + user.getBelt());

        ArrayList<GymClassEntity> gymClassesForKevin =
                drq.getClassesUserEnrolledInByEmail("kevin@gmail.com");
        System.out.println(gymClassesForKevin.get(0).getStartDateTime());
        System.out.println(gymClassesForKevin.get(1).getStartDateTime());
        ArrayList<TrainerAvailabilityEntity> sarahsAvailabilities =
                drq.getTrainerDateTimeAvailabilityByEmail("sarah@gmail.com");
        System.out.println("size of trainer sarah Availabilities is "
                + sarahsAvailabilities.size());

        LocalDate date = LocalDate.of(2020,4,11);
        ArrayList<GymClassEntity> classes = drq.getAllClassesByDate(date);
        System.out.println("size of all classes is " + classes.size());

        RoleEntity admin = new RoleEntity(RoleEntity.UserRole.admin);
        ArrayList<UserEntity> admins = drq.getAllUsersOfCertainRole(admin);
        System.out.println("size of all admins is " + admins.size());
    }
}
