package test.java;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import main.java.memoranda.database.entities.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.ui.LoginBox;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class loginTest {

    static LoginBox login;
    public static SqlConnection sqlConnection;

    @BeforeClass
    public static void setUp() throws SQLException {
        sqlConnection = SqlConnection.getInstance();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();

        login = new LoginBox();
        sqlConnection.getDcqTest().insertUser("Test@Test.com", "TestFirst", "TestLast",
                "TestPass", new RoleEntity(RoleEntity.UserRole.trainer));

        login.getEmail().setText("Test@Test.com");
        login.getPassword().setText("TestPass");

    }

    @AfterClass
    public static void tearDown() throws SQLException {
        sqlConnection.getDbSetupHelperTest().closeDatabase();
        sqlConnection.getDbd().deleteUser("Test@Test.com");
        // Close the login GUI
        login.dispose();
    }

    @Test
    public void accountExistsTest() throws SQLException {
        UserEntity user = sqlConnection.getDrqTest().getUserByEmail(login.getEmail().getText());
        assertTrue(login.accountExists(user));
    }

    @Test
    public void accountDoesNotExistTest() throws SQLException {
        UserEntity user2 = sqlConnection.getDrqTest().getUserByEmail("User@User.com");
        assertTrue(!login.accountExists(user2));
    }

    @Test
    public void passwordIsCorrectTest() throws SQLException {
        UserEntity user3 = sqlConnection.getDrqTest().getUserByEmail(login.getEmail().getText());
        assertTrue(login.passwordIsCorrect(user3.getPassword()));
    }
}