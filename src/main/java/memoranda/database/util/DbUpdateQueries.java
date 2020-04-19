package main.java.memoranda.database.util;

import main.java.memoranda.database.BeltEntity;
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
                           BeltEntity belt) throws SQLException {

        Connection conn = DriverManager.getConnection(_dbUrl);
        String query = "UPDATE USER SET Email = ?, FirstName = ?, LastName = ?, Role = ?, Belt = ? " +
                "where password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, role.userRole.name());
        pstmt.setString(5, belt.rank.name());
        pstmt.setString(6, password);
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
                           BeltEntity trainingBelt) throws SQLException {

        Connection conn = DriverManager.getConnection(_dbUrl);

        String query = "UPDATE USER SET Email = ?, FirstName = ?, LastName = ?, Role = ?, Belt = ?, " +
                "TrainingBelt = ? where password = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, firstName);
        pstmt.setString(3, lastName);
        pstmt.setString(4, role.userRole.name());
        pstmt.setString(5, belt.rank.name());
        pstmt.setString(6, trainingBelt.rank.name());
        pstmt.setString(7, password);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
}
