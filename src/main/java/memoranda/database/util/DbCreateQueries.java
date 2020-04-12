package main.java.memoranda.database.util;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;

import java.sql.*;

/*
helpful utility class for various create (insert) queries
 */
public class DbCreateQueries {

    private String _dbUrl;

    public DbCreateQueries(String url) {
        this._dbUrl = url;
    }

    /*
    add a new user to the USER table, example usage:
    RoleEntity role = new RoleEntity(RoleEntity.UserRole.customer);
    insertUser("steve@gmail.com", "steve", "johnson", "foofoo", role);
    */
    public void insertUser(String email, String firstName, String lastName, String password, RoleEntity role) throws SQLException {
        String sql = "INSERT INTO USER(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";
        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setNull(6, Types.NULL);
        pstmt.setNull(7, Types.NULL);
        pstmt.executeUpdate();

    }
    /*
    add a new user to the USER table, example usage:
    RoleEntity role = new RoleEntity(RoleEntity.UserRole.trainer);
    BeltEntity belt = new BeltEntity(BeltEntity.Rank.white);
    BeltEntity trainingBelt = new BeltEntity(BeltEntity.rank.white);
    insertUser("steve@gmail.com", "steve", "johnson", "foofoo", role, belt, trainingBelt);
     */
    public void insertUser(String email,
                            String firstName,
                            String lastName,
                            String password,
                            RoleEntity role,
                            BeltEntity belt,
                            BeltEntity trainingBelt) throws SQLException {
        String sql = "INSERT INTO USER(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";
        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setString(6, belt.rank.name());
        pstmt.setString(7, trainingBelt.rank.name());
        pstmt.executeUpdate();

    }
    /*
    add a new class to the GYMCLASS table, example usage:
    BeltEntity minBeltRequired = new BeltEntity(BeltEntity.Rank.white);
    insertClass(1, "04/11/2020", 12.0, 13.0, "kevin@gmail.com", 20, minBeltRequired, "admin@gmail.com");
    Note that start time and end time is a double, 0.0 indicates midnight and 23.99 is right before midnight
     */
    public void insertClass(int roomNumber,
                             String startDate,
                             double startTime,
                             double endTime,
                             String trainerEmail,
                             int maxClassSize,
                             BeltEntity minBeltRequired,
                             String createdByEmail) throws SQLException {
        String sql = "INSERT INTO GYMCLASS(RoomNumber,StartDate,StartTime,EndTime,TrainerEmail,MaxClassSize,MinBeltRequired,CreatedByEmail) VALUES(?,?,?,?,?,?,?,?)";
        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roomNumber);
        pstmt.setString(2, startDate);
        pstmt.setDouble(3, startTime);
        pstmt.setDouble(4, endTime);
        pstmt.setString(5, trainerEmail);
        pstmt.setInt(6, maxClassSize);
        pstmt.setString(7, minBeltRequired.rank.name());
        pstmt.setString(8, createdByEmail);
        pstmt.executeUpdate();
    }
    /*
    insert a new trainer availability into TRAINERAVAILABILITY, example usage:
    insertTrainerAvailability("BunsOfSteel@gmail.com", "04/12/2020", 8.0, 9.0);
     */
    public void insertTrainerAvailability(String trainerEmail, String startDate, double startTime, double endTime) throws SQLException {
        String sql = "INSERT INTO TRAINERAVAILABILITY(TrainerEmail,StartDate,StartTime,EndTime) VALUES(?,?,?,?)";
        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trainerEmail);
        pstmt.setString(2, startDate);
        pstmt.setDouble(3, startTime);
        pstmt.setDouble(4, endTime);
        pstmt.executeUpdate();
    }
    /*
    insert a new user that's signed up for a class (add user to EnrolledUser), example usage:
    insertEnrolledUser(11, "jeff@gmail.com");
     */
    public void insertEnrolledUser(int classId, String userEmail) throws SQLException {
        String sql = "INSERT INTO ENROLLEDUSER(ClassId,UserEmail) VALUES(?,?)";
        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, classId);
        pstmt.setString(2, userEmail);
        pstmt.executeUpdate();

    }
}
