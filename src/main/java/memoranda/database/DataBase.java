package main.java.memoranda.database;

import main.java.memoranda.database.util.SqlConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    public Connection conn = null;

    public void connect() throws SQLException{
        this.conn = DriverManager.getConnection(SqlConstants.DEFAULTDBLOC);
    }

    public Connection getConnection(){
        return this.conn;
    }

}
