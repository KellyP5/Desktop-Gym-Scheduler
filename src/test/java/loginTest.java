package test.java;

import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.ui.LoginBox;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class loginTest {

    static LoginBox login;
    public static SqlConnection sqlConnection;
    private static String imageUrl;

    @BeforeClass
    public static void setUp() throws SQLException {
        sqlConnection = SqlConnection.getInstance();
        sqlConnection.getDbSetupHelperTest().deleteTestTables();
        sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
        imageUrl = "src/main/resources/ui/Placeholder.png";

        login = new LoginBox();
        sqlConnection.getDcqTest().insertUser("Test@Test.com", "TestFirst", "TestLast",
                "TestPass", new RoleEntity(RoleEntity.UserRole.trainer), imageUrl);

        login.getEmail().setText("Test@Test.com");
        login.getPassword().setText("TestPass");

    }

    @AfterClass
    public static void tearDown() throws SQLException {
        sqlConnection.getDbSetupHelperTest().closeDatabase();
        sqlConnection.getDcq().deleteUser("Test@Test.com");
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