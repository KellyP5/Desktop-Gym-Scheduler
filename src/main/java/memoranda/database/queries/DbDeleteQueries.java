package main.java.memoranda.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.memoranda.database.util.EnforcedConnection;

public class DbDeleteQueries {


    private String _dbUrl;

    public DbDeleteQueries(String dbUrl) {
        this._dbUrl = dbUrl;
    }

    public void deleteClass(int roomNumber, String startDate, double startTime)
        throws SQLException {
        String sql = "DELETE FROM GYMCLASS WHERE RoomNumber=? AND " +
            "StartDate=? AND " +
            "StartTime=?;";
        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roomNumber);
        pstmt.setString(2, startDate);
        pstmt.setDouble(3, startTime);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    /**
     * Delete a user from the DB based on e-mail
     *
     * @param userEmail
     * @throws SQLException
     */
    public void deleteUser(String userEmail) throws SQLException {
        String sql = "DELETE FROM USER WHERE Email=?; DELETE FROM ENROLLEDUSER WHERE UserEmail=?";
        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userEmail);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
