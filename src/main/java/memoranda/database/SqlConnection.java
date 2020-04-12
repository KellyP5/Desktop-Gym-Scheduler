package main.java.memoranda.database;

import main.java.memoranda.database.util.SqlConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
SqlConnection is a singleton pattern.  This class may only be needed if we have multiple threads that are reading/writing
to the database.  Until users experience issues, they can utilize the Db___Queries classes directly.
 */
public class SqlConnection {
    private static SqlConnection _instance = null;

    private SqlConnection(){

    }

    /*
    Utilized for singleton pattern, returns new instance if not already in existance
     */
    public static SqlConnection get_instance() {
        if(_instance == null){
            _instance = new SqlConnection();
        }
        return _instance;
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
        return getConnection(SqlConstants.DEFAULTDBLOC);
    }
    public Connection getTestConnection() throws SQLException{
        return getConnection(SqlConstants.DEFAULTTESTDBLOC);
    }
}
