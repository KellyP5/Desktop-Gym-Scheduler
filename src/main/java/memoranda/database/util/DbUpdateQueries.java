package main.java.memoranda.database.util;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.database.RoleEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUpdateQueries {

    private String _dbUrl;

    public DbUpdateQueries(String dbUrl) {
        this._dbUrl = dbUrl;
    }

    // Update student
    public void updateUser(String email,
                           String firstName,
                           String lastName,
                           String password,
                           RoleEntity role,
                           BeltEntity belt,
                           String imageUrl) throws SQLException {

        Connection conn = DriverManager.getConnection(_dbUrl);
        String query = "UPDATE USER SET Email = ?, FirstName = ?, LastName = ?, Role = ?, Belt = ?, ImageURL = ? " +
                "where password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, role.userRole.name());
        pstmt.setString(5, belt.rank.name());
        pstmt.setString(6, imageUrl);
        pstmt.setString(7, password);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    // Update trainer/admin
    public void updateUser(String email,
                           String firstName,
                           String lastName,
                           String password,
                           RoleEntity role,
                           BeltEntity belt,
                           BeltEntity trainingBelt,
                           String imageUrl) throws SQLException {

        Connection conn = DriverManager.getConnection(_dbUrl);

        String query = "UPDATE USER SET Email = ?, FirstName = ?, LastName = ?, Role = ?, Belt = ?, " +
                "TrainingBelt = ?, ImageURL = ? where password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, role.userRole.name());
        pstmt.setString(5, belt.rank.name());
        pstmt.setString(6, trainingBelt.rank.name());
        pstmt.setString(7, imageUrl);
        pstmt.setString(8, password);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    /**
     * Updates a class in the DB. Does a check if a class into the moving room
     * and time already exists. If it does, returns false.
     * @param roomNumber
     * @param startDate
     * @param startTime
     * @param endTime
     * @param trainerEmail
     * @param maxClassSize
     * @param minBeltRequired
     * @param createdByEmail
     * @param oldRoom
     * @param oldStart
     * @return boolean of whether the update happened or not.
     * @throws SQLException
     */
    public boolean updateClass(int roomNumber,
                            LocalDate startDate,
                            double startTime,
                            double endTime,
                            String trainerEmail,
                            int maxClassSize,
                            BeltEntity minBeltRequired,
                            String createdByEmail,
                            int oldRoom,
                            double oldStart) throws SQLException {
        if (oldStart != startTime || oldRoom != roomNumber) {
            String strDate = startDate.format(SqlConstants.DBDATEFORMAT);
            String sql = "SELECT * FROM GYMCLASS WHERE StartDate=?" +
                "AND StartTime=?" +
                "AND RoomNumber=?";
            Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setString(1,strDate);
            pstmt.setDouble(2,startTime);
            pstmt.setInt(3, roomNumber);
            ResultSet rs  = pstmt.executeQuery();
            if (rs.next() != false) return false;
            pstmt.close();
            conn.close();
        }
        String strDate = startDate.format(SqlConstants.DBDATEFORMAT);
        String sql = "UPDATE GYMCLASS SET RoomNumber = ?, StartDate = ?, StartTime = ?, EndTime = ?," +
            "TrainerEmail = ?, MaxClassSize = ?, MinBeltRequired = ?, CreatedByEmail = ?" +
            "WHERE RoomNumber = ? AND StartTime = ? AND StartDate = ?";
        System.out.println(sql);
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
        pstmt.setInt(9, oldRoom);
        pstmt.setDouble(10, oldStart);
        pstmt.setString(11, strDate);

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
        return true;
    }
}
