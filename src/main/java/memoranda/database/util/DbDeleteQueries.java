package main.java.memoranda.database.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbDeleteQueries {


    private String _dbUrl;

    public DbDeleteQueries(String dbUrl) {
        this._dbUrl = dbUrl;
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
