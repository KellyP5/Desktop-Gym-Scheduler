package main.java.memoranda.database;

import java.sql.Connection;
import java.sql.SQLException;
import main.java.memoranda.database.queries.DbCreateQueries;
import main.java.memoranda.database.queries.DbDeleteQueries;
import main.java.memoranda.database.queries.DbReadQueries;
import main.java.memoranda.database.util.DbSetupHelper;
import main.java.memoranda.database.queries.DbUpdateQueries;
import main.java.memoranda.database.util.EnforcedConnection;
import main.java.memoranda.database.util.SqlConstants;

/*
SqlConnection is a singleton pattern that ensures only one connection is made to each database (real and test)
 */
public class SqlConnection {

    private DbCreateQueries dcq;
    private DbCreateQueries dcqTest;

    private DbReadQueries drq;
    private DbReadQueries drqTest;

    private DbUpdateQueries duq;
    private DbUpdateQueries duqTest;

    private DbDeleteQueries dbd;
    private DbDeleteQueries dbdTest;

    private DbSetupHelper dbSetupHelper;
    private DbSetupHelper dbSetupHelperTest;

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
        Connection realDbConn = EnforcedConnection.getEnforcedCon(SqlConstants.DEFAULTDBLOC);
        Connection testDbConn = EnforcedConnection.getEnforcedCon(SqlConstants.DEFAULTTESTDBLOC);


        drq = new DbReadQueries(SqlConstants.DEFAULTDBLOC);
        drqTest = new DbReadQueries(SqlConstants.DEFAULTTESTDBLOC);
        dcq = new DbCreateQueries(SqlConstants.DEFAULTDBLOC);
        dcqTest = new DbCreateQueries(SqlConstants.DEFAULTTESTDBLOC);
        dbSetupHelper = new DbSetupHelper(SqlConstants.DEFAULTDBLOC);
        dbSetupHelperTest = new DbSetupHelper(SqlConstants.DEFAULTTESTDBLOC);
        duq = new DbUpdateQueries(SqlConstants.DEFAULTDBLOC);
        duqTest = new DbUpdateQueries(SqlConstants.DEFAULTTESTDBLOC);

        this.dbd = new DbDeleteQueries(SqlConstants.DEFAULTDBLOC);
        this.dbdTest = new DbDeleteQueries(SqlConstants.DEFAULTTESTDBLOC);

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


    public static void close(){
        _instance = null;
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

    public DbDeleteQueries getDbd(){return this.dbd;}

    public DbDeleteQueries getDbdTest(){return this.dbdTest;}
}
