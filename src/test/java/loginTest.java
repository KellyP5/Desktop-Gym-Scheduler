package test.java;

/*
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.database.util.EnforcedConnection;
import main.java.memoranda.database.util.SqlConstants;
import main.java.memoranda.ui.App;
import org.junit.*;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import main.java.memoranda.ui.LoginBox;
import static org.junit.Assert.assertTrue;

public class loginTest {

    static LoginBox login;
    static App app;
    public static SqlConnection sqlConnection;

    @BeforeClass
    public static void setUp() throws SQLException, IOException {
        sqlConnection = SqlConnection.getInstance();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();

        app = new App(true, login.conn);
        sqlConnection.getDcqTest().insertUser("Test@Test.com", "TestFirst", "TestLast",
                "TestPass", new RoleEntity(RoleEntity.UserRole.trainer));

        login = new LoginBox();
        login.getEmail().setText("Test@Test.com");
        login.getPassword().setText("TestPass");

    }

    @AfterClass
    public static void tearDown() throws SQLException {
        sqlConnection.getDbSetupHelperTest().closeDatabase();
        // Close the login GUI
        login.dispose();
    }

//    @Test
//    public void userVerificationTest() throws SQLException {
//        assertTrue(login.userVerification());
//    }

    @Test
    public void accountExistsTest() throws SQLException {
        UserEntity user = app.conn.getDrqTest().getUserByEmail(login.getEmail().getText());
        assertTrue(login.accountExists(user));

    }

    @Test
    public void accountDoesNotExistTest() throws SQLException {
        UserEntity user2 = app.conn.getDrqTest().getUserByEmail("User@User.com");
        assertTrue(!login.accountExists(user2));
    }


    @Test
    public void passwordIsCorrectTest() throws SQLException {
        UserEntity user3 = app.conn.getDrqTest().getUserByEmail(login.getEmail().getText());
        assertTrue(login.passwordIsCorrect(user3.getPassword()));
    }

}
*/
