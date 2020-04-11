package main.java.memoranda.database.util;

import main.java.memoranda.database.RoleEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSetupHelper {

    public static void main(String[] args) {
        //create primary db
        DbSetupHelper dbSetupHelper = new DbSetupHelper();
        dbSetupHelper.createDatabase();
        dbSetupHelper.createNeujahrskranzTables();
        //create test db
        dbSetupHelper.setUrl(dbSetupHelper.defaultTestLoc);
        dbSetupHelper.createDatabase();
        dbSetupHelper.createNeujahrskranzTables();
    }

    public final String defaultLoc = "jdbc:sqlite:src\\main\\resources\\database\\real.db";
    public final String defaultTestLoc = "jdbc:sqlite:src\\main\\resources\\database\\test.db";
    private String url;

    public DbSetupHelper() {
        this.url = defaultLoc;
    }
    public DbSetupHelper(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void createDatabase(){
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void closeDatabase(){
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(url);
            conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("failed to close!");
        }
    }
    public void createTable(String sql, String tableName){
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table: " + tableName + " was created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createNeujahrskranzTables(){
        createDatabase();
        System.out.println("Preparing to create tables..note that if the tables already exist they will not be created");
        String userSql = "CREATE TABLE IF NOT EXISTS USER (\n"
                + "    Email text PRIMARY KEY,\n"
                + "    FirstName text NOT NULL,\n"
                + "    LastName text NOT NULL,\n"
                + "    Password text NOT NULL,\n"
                + "    Role text NOT NULL,\n"
                + "    Belt text,\n"
                + "    TrainingBelt text\n"
                + ");";
        createTable(userSql, "User");
        String classSql = "CREATE TABLE IF NOT EXISTS GYMCLASS (\n"
                + "    Id integer PRIMARY KEY,\n"
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
        String trainerAvailSql = "CREATE TABLE IF NOT EXISTS TRAINERAVAILABILITY (\n"
                + "    TrainerEmail text PRIMARY KEY,\n"
                + "    StartDate text NOT NULL,\n"
                + "    StartTime real NOT NULL,\n"
                + "    EndTime real NOT NULL,\n"
                + "    FOREIGN KEY(TrainerEmail) REFERENCES USER(Email) ON DELETE CASCADE\n"
                + ");";
        createTable(trainerAvailSql, "TrainerAvailability");
        String enrolledUsersSql = "CREATE TABLE IF NOT EXISTS ENROLLEDUSER (\n"
                + "    ClassId integer PRIMARY KEY,\n"
                + "    UserEmail text NOT NULL,\n"
                + "    FOREIGN KEY(UserEmail) REFERENCES USER(Email) ON DELETE CASCADE\n"
                + "    FOREIGN KEY(ClassId) REFERENCES GYMCLASS(Id) ON DELETE CASCADE\n"
                + ");";
        createTable(enrolledUsersSql, "EnrolledUser");
    }
    public void addSampleData(){
        //TODO
    }

    private void insertUser(String email, String firstName, String lastName, String password, RoleEntity role){
        //TODO

    }
}
