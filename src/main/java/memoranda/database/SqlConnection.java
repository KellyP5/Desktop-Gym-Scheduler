package main.java.memoranda.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlConnection {
    private static SqlConnection instance = null;
    private final String jdbcUrl = "jdbc:sqlite:/main/resources/database/real.db";
    private final String jdbcTestUrl = "jdbc:sqlite:/main/resources/database/test.db";

    private SqlConnection(){

    }

    public static SqlConnection getInstance() {
        if(instance == null){
            instance = new SqlConnection();
        }
        return instance;
    }

    private Connection getConnection(String url) throws SQLException{
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Cannot connect to database", e);
        }
    }


    public Connection getConnection() throws SQLException{
        return getConnection(this.jdbcUrl);
    }
    public Connection getTestConnection() throws SQLException{
        return getConnection(this.jdbcTestUrl);
    }
}
