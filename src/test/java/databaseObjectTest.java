package test.java;

import main.java.memoranda.database.*;
import main.java.memoranda.database.util.DbCreateQueries;
import main.java.memoranda.database.util.DbReadQueries;
import main.java.memoranda.database.util.SqlConstants;
import org.junit.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class databaseObjectTest {

    @BeforeClass
    public static void setUp() throws Exception {

    }

    @AfterClass
    public static void tearDown() throws Exception {

    }

    @Test
    public void userInsertedThenRetrievedFromDbIsEqual() throws SQLException {

    }
}
