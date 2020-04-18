package main.java.memoranda.database;

import main.java.memoranda.database.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
SqlConnection is a singleton pattern that ensures only one connection is made to each database (real and test)
 */
public class SqlConnection {

    private DbReadQueries drq;
    private DbReadQueries drqTest;
    private DbCreateQueries dcq;
    private DbCreateQueries dcqTest;
    private DbSetupHelper dbSetupHelper;
    private DbSetupHelper dbSetupHelperTest;
    private DbUpdateQueries duq;
    private DbUpdateQueries duqTest;

    /*
    This is just a helper main that can be ran to generate the real and test databases.
     */
    public static void main(String[] args) throws SQLException {
        SqlConnection sqlConnection = SqlConnection.getInstance();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
        sqlConnection.getDbSetupHelperTest().addSampleDataToDb(sqlConnection.getDcqTest());
        sqlConnection.getDbSetupHelper().createNeujahrskranzTables();
    }


    private static SqlConnection _instance = null;

    private SqlConnection() throws SQLException {
        Connection realDbConn = DriverManager.getConnection(SqlConstants.DEFAULTDBLOC);
        Connection testDbConn = DriverManager.getConnection(SqlConstants.DEFAULTTESTDBLOC);


        drq = new DbReadQueries(SqlConstants.DEFAULTDBLOC);
        drqTest = new DbReadQueries(SqlConstants.DEFAULTTESTDBLOC);
        dcq = new DbCreateQueries(SqlConstants.DEFAULTDBLOC);
        dcqTest = new DbCreateQueries(SqlConstants.DEFAULTTESTDBLOC);
        dbSetupHelper = new DbSetupHelper(SqlConstants.DEFAULTDBLOC);
        dbSetupHelperTest = new DbSetupHelper(SqlConstants.DEFAULTTESTDBLOC);
        duq = new DbUpdateQueries(SqlConstants.DEFAULTDBLOC);
        duqTest = new DbUpdateQueries(SqlConstants.DEFAULTTESTDBLOC);
    }

    /*
    Utilized for singleton pattern, returns new instance if not already in existance
     */
    public static SqlConnection getInstance() throws SQLException {
        if(_instance == null){
            _instance = new SqlConnection();
        }
        return _instance;
    }

    public DbReadQueries getDrq() {
        return drq;
    }

    public DbReadQueries getDrqTest() {
        return drqTest;
    }

    public DbCreateQueries getDcq() {
        return dcq;
    }

    public DbCreateQueries getDcqTest() {
        return dcqTest;
    }

    public DbSetupHelper getDbSetupHelper() {
        return dbSetupHelper;
    }

    public DbSetupHelper getDbSetupHelperTest() {
        return dbSetupHelperTest;
    }

    public DbUpdateQueries getDuq() {
        return duq;
    }

    public DbUpdateQueries getDuqTest() {
        return duqTest;
    }
}
