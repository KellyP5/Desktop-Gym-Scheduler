package main.java.memoranda.database.querries;

import main.java.memoranda.database.DataBase;
import main.java.memoranda.database.entities.BeltEntity;
import main.java.memoranda.database.entities.RoleEntity;

import java.sql.*;

/*
helpful utility class for various create (insert) queries
 */
public class DbCreateQueries {


    public static void insertUser(DataBase server,
                                  String email,
                                  String firstName,
                                  String lastName,
                                  String password,
                                  RoleEntity role) throws SQLException {
        String sql = "INSERT INTO USER" +
                "(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";

        Connection conn = server.getConnection();
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

    public static void insertUser(DataBase server,
                             String email,
                            String firstName,
                            String lastName,
                            String password,
                            RoleEntity role,
                            BeltEntity belt,
                            BeltEntity trainingBelt) throws SQLException {
        String sql = "INSERT INTO USER" +
                "(Email,FirstName,LastName,Password,Role,Belt,TrainingBelt) VALUES(?,?,?,?,?,?,?)";

        Connection conn = server.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, password);
        pstmt.setString(5, role.userRole.name());
        pstmt.setString(6, belt.rank.name());
        pstmt.setString(7, trainingBelt.rank.name());
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public static void insertClass(DataBase server,
                                   int roomNumber,
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

        Connection conn = server.getConnection();
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

    public static void insertClass(DataBase server,
                                   int id,
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

        Connection conn = server.getConnection();
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



    public static void insertTrainerAvailability(DataBase server,
                                                 String trainerEmail,
                                          String startDate,
                                          double startTime,
                                          double endTime) throws SQLException {
        String sql = "INSERT INTO TRAINERAVAILABILITY" +
                "(TrainerEmail,StartDate,StartTime,EndTime) VALUES(?,?,?,?)";

        Connection conn = server.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trainerEmail);
        pstmt.setString(2, startDate);
        pstmt.setDouble(3, startTime);
        pstmt.setDouble(4, endTime);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public static void insertEnrolledUser(DataBase server,
                                          int classId, String userEmail) throws SQLException {
        String sql = "INSERT INTO ENROLLEDUSER(ClassId,UserEmail) VALUES(?,?)";

        Connection conn = server.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, classId);
        pstmt.setString(2, userEmail);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
}
