package main.java.memoranda.database.util;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Class used for getting the jdbc connection, which enforces that foreign keys constraints are held. This is necessary
because by default sqlite does not enforce foreign key constraints.
 */
public class EnforcedConnection {

    /**
     * returns a connection that has database constraint enforced.
     * @param db_url string path to the database location, including the connector syntax
     * @return connection is a connection to the database with FK enforcement
     * @throws SQLException
     */
    public static Connection getEnforcedCon(String db_url) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        return DriverManager.getConnection(db_url,config.toProperties());
    }
}
