package main.java.memoranda.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
SqlConnection is a singleton pattern.  This class may only be needed if we have multiple threads that are reading/writing
to the database.  Until users experience issues, they can utilize the Db___Queries classes directly.
 */
public class SqlConnection {
    private static SqlConnection instance = null;
    private final String jdbcUrl = "jdbc:sqlite:/main/resources/database/real.db";
    private final String jdbcTestUrl = "jdbc:sqlite:/main/resources/database/test.db";

    private SqlConnection(){

    }

    /*
    Utilized for singleton pattern, returns new instance if not already in existance
     */
    public static SqlConnection getInstance() {
        if(instance == null){
            instance = new SqlConnection();
        }
        return instance;
    }

    /*
    returns the connection to the database at url
     */
    private Connection getConnection(String url) throws SQLException{
        try {
            return DriverManager.getConnection(url);
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
