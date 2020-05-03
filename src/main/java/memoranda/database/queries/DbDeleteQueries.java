package main.java.memoranda.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import main.java.memoranda.database.util.EnforcedConnection;
import main.java.memoranda.database.util.SqlConstants;

public class DbDeleteQueries {


    private String _dbUrl;

    public DbDeleteQueries(String dbUrl) {
        this._dbUrl = dbUrl;
    }

    public void deleteClass(int roomNumber, LocalDate startDate, double startTime)
        throws SQLException {
        String strDate = startDate.format(SqlConstants.DBDATEFORMAT);
        String sql = "DELETE FROM GYMCLASS WHERE RoomNumber=? AND " +
            "StartDate=? AND " +
            "StartTime=?";
        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, roomNumber);
        pstmt.setString(2, strDate);
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


    /**Unenroll a user from a class by deleting them from the ENROLLEDUSER table.
     *
     * @param userEmail email of the user which will be unenrolled
     * @param classId class id of the class the user will be unenrolled from.
     * @throws SQLException
     */
    public void unenrollUser(String userEmail, int classId) throws SQLException {
        String sql = "DELETE FROM ENROLLEDUSER WHERE UserEmail=? AND ClassId=?";
        Connection conn = EnforcedConnection.getEnforcedCon(_dbUrl);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userEmail);
        pstmt.setInt(2, classId);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
