package main.java.memoranda.database;

import java.sql.SQLException;

/*
SqlConnection is a singleton pattern that ensures only one connection is made to each database (real and test)
 */
public class SqlConnection {




    private static SqlConnection _instance = null;

    private SqlConnection() throws SQLException {

    }



}
