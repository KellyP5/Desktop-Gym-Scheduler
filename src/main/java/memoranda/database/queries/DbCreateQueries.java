package main.java.memoranda.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import main.java.memoranda.database.entities.BeltEntity;
import main.java.memoranda.database.entities.RoleEntity;
import main.java.memoranda.database.util.EnforcedConnection;
import main.java.memoranda.database.util.SqlConstants;

/*
helpful utility class for various create (insert) queries
 */
public class DbCreateQueries {

    private String _dbUrl;

    public DbCreateQueries(String dbUrl) {
        this._dbUrl = dbUrl;
    }

    //TODO This method should not exist. The database should not allow the creation of a user without a belt.
    public void insertUser(String email,
                           String firstName,
                           String lastName,
                           String password,
                           RoleEntity role) throws SQLException {
        String sql = "INSERT INTO USER" +
            "(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setNull(6, Types.NULL);
        pstmt.setNull(7, Types.NULL);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    //Admin and Trainer uses this
    public void insertUser(String email,
                           String firstName,
                           String lastName,
                           String password,
                           RoleEntity role,
                           BeltEntity startingBelt,
                           BeltEntity trainingBelt) throws SQLException {

        String sql = "INSERT INTO USER" +
            "(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setString(6, startingBelt.toString());
        pstmt.setString(7, trainingBelt.toString());
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
    //Customer uses this
    public void insertUser(String email,
                           String firstName,
                           String lastName,
                           String password,
                           RoleEntity role,
                           BeltEntity startingBelt) throws SQLException {

        String sql = "INSERT INTO USER" +
            "(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setString(6, startingBelt.toString());
        pstmt.setString(7, null);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    //TODO startDate time as a string doesn't make sense to use as per KELLY discussion
    public void insertClass(int roomNumber,
                            String startDate,
                            double startTime,
                            double endTime,
                            String trainerEmail,
                            int maxClassSize,
                            BeltEntity minBeltRequired,
                            String createdByEmail) throws SQLException {
        String sql = "INSERT INTO GYMCLASS" +
            "(RoomNumber,StartDate,StartTime,EndTime,TrainerEmail,MaxClassSize,MinBeltRequired," +
            "CreatedByEmail) VALUES(?,?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
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

        pstmt.close();
        conn.close();
    }

    /**
     * Inserts class but accounts for LocalDate format.
     *
     * @param roomNumber
     * @param startDate
     * @param startTime
     * @param endTime
     * @param trainerEmail
     * @param maxClassSize
     * @param minBeltRequired
     * @param createdByEmail
     * @throws SQLException
     */
    public void insertClass(int roomNumber,
                            LocalDate startDate,
                            double startTime,
                            double endTime,
                            String trainerEmail,
                            int maxClassSize,
                            BeltEntity minBeltRequired,
                            String createdByEmail) throws SQLException {
        String strDate = startDate.format(SqlConstants.DBDATEFORMAT);
        String sql = "INSERT INTO GYMCLASS" +
            "(RoomNumber,StartDate,StartTime,EndTime,TrainerEmail,MaxClassSize,MinBeltRequired," +
            "CreatedByEmail) VALUES(?,?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roomNumber);
        pstmt.setString(2, strDate);
        pstmt.setDouble(3, startTime);
        pstmt.setDouble(4, endTime);
        pstmt.setString(5, trainerEmail);
        pstmt.setInt(6, maxClassSize);
        pstmt.setString(7, minBeltRequired.rank.name());
        pstmt.setString(8, createdByEmail);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    //TODO This method should not exist. No one will have any idea on what the ID is to insert the class.
    public void insertClass(int id,
                            int roomNumber,
                            String startDate,
                            double startTime,
                            double endTime,
                            String trainerEmail,
                            int maxClassSize,
                            BeltEntity minBeltRequired,
                            String createdByEmail) throws SQLException {
        String sql = "INSERT INTO GYMCLASS" +
            "(Id, RoomNumber,StartDate,StartTime,EndTime,TrainerEmail,MaxClassSize,MinBeltRequired," +
            "CreatedByEmail) VALUES(?,?,?,?,?,?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setInt(2, roomNumber);
        pstmt.setString(3, startDate);
        pstmt.setDouble(4, startTime);
        pstmt.setDouble(5, endTime);
        pstmt.setString(6, trainerEmail);
        pstmt.setInt(7, maxClassSize);
        pstmt.setString(8, minBeltRequired.rank.name());
        pstmt.setString(9, createdByEmail);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }


    /*
    insert a new trainer availability into TRAINERAVAILABILITY, example usage:
    insertTrainerAvailability("BunsOfSteel@gmail.com", "04/12/2020", 8.0, 9.0);
     */
    public void insertTrainerAvailability(String trainerEmail,
                                          String startDate,
                                          double startTime,
                                          double endTime) throws SQLException {
        String sql = "INSERT INTO TRAINERAVAILABILITY" +
            "(TrainerEmail,StartDate,StartTime,EndTime) VALUES(?,?,?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trainerEmail);
        pstmt.setString(2, startDate);
        pstmt.setDouble(3, startTime);
        pstmt.setDouble(4, endTime);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    /*
    insert a new user that's signed up for a class (add user to EnrolledUser), example usage:
    insertEnrolledUser(11, "jeff@gmail.com");
     */
    public void insertEnrolledUser(int classId, String userEmail) throws SQLException {
        String sql = "INSERT INTO ENROLLEDUSER(ClassId,UserEmail) VALUES(?,?)";

        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, classId);
        pstmt.setString(2, userEmail);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }






}
