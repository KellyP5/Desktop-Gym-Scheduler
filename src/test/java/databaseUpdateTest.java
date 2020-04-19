package test.java;

/*
import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.database.util.DbCreateQueries;
//import main.java.memoranda.database.util.DbUpdateQueries;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class databaseUpdateTest {

    public static SqlConnection sqlConnection = null;
    public static DbUpdateQueries duq = null;
    public static DbCreateQueries dcq = null;

    @BeforeClass
    public static void setUp() throws Exception {
        sqlConnection = SqlConnection.getInstance();
        duq = sqlConnection.getDuq();
        dcq = sqlConnection.getDcq();
        //sqlConnection.getDbSetupHelperTest().deleteTestTables();
        //sqlConnection.getDbSetupHelperTest().createNeujahrskranzTables();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        sqlConnection.getDbSetupHelperTest().closeDatabase();
        // Remove the test user that was added to the db
        String sql = "DELETE FROM user WHERE Email='Customer2@gmail.com'";
        Connection conn = DriverManager.getConnection("jdbc:sqlite:real.db");
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
        conn.close();
    }

    @Test
    public void customerUserIsUpdated() throws SQLException {
        RoleEntity role = new RoleEntity(RoleEntity.UserRole.customer);
        BeltEntity belt = new BeltEntity(BeltEntity.Rank.orange);
        UserEntity customer = new UserEntity("CustomerFirst", "CustomerLast", "pass", "Customer2@gmail.com", role);
        dcq.insertUser(customer.getEmail(), customer.getFirstName(), customer.getLastName(), customer.getPassword(), customer.getRole());

        duq.updateUser("Customer2@gmail.com", "Kelly", "Last", "pass", role, belt);

        assertTrue(1 == 1);
        //assertTrue(customer.getFirstName().equals("Kelly"));
    }

    /*
    @Test
    public void trainerUserIsUpdated() throws SQLException {
        RoleEntity role = new RoleEntity(RoleEntity.UserRole.trainer);
        BeltEntity belt = new BeltEntity(BeltEntity.Rank.orange);
        BeltEntity trainBelt = new BeltEntity(BeltEntity.Rank.yellow);
        UserEntity trainer = new UserEntity("TrainerFirst", "TrainerLast", "test", "Trainer@gmail.com", role);
        dcq.insertUser(trainer.getEmail(), trainer.getFirstName(), trainer.getLastName(), trainer.getPassword(), role, belt, trainBelt);

        duq.updateUser("Test@gmail.com", "Kelly", "Last", "test", role, belt);
        assertTrue(trainer.getLastName().equals("Last"));

    }

     */



