package main.java.memoranda.database.util;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;

/*
Constants to be used for database related information
 */
public class SqlConstants {
    public static final String DEFAULTDBLOC = "jdbc:sqlite:real.db";
    public static final String DEFAULTTESTDBLOC =
            "jdbc:sqlite:test.db";
    public static final DateTimeFormatter DBDATEFORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter DBTIMEFORMAT = DateTimeFormatter.ofPattern("HH.mm");
}
