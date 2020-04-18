package test.java;

        import main.java.memoranda.database.*;
        import main.java.memoranda.database.util.DbCreateQueries;
        import main.java.memoranda.database.util.DbReadQueries;
        import main.java.memoranda.database.util.SqlConstants;
        import org.junit.*;

        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.time.LocalTime;
        import java.util.ArrayList;

        import static org.junit.Assert.*;


public class databaseObjectTest {
    public static EnrolledUserEntity eu1;
    public static BeltEntity be1, be2, be3;
    public static GymClassEntity gce1;
    public static LocalDateTime ldt1;

    @BeforeClass
    public static void setUp() {
        eu1 = new EnrolledUserEntity(1, "kjpetron@asu.edu");
        be1 = new BeltEntity("");
        be2 = new BeltEntity("black3");
        be3 = new BeltEntity("black3");
        ldt1 = LocalDateTime.now();
        gce1 = new GymClassEntity(1, 1, ldt1, ldt1,
                "kjpetron@asu.edu",
                10, be1,
                "admin@gym.com");
    }

    @Test
    public void beltEntity() {
        assertEquals(be1.toString(), "white");
        assertEquals(be2.toString(), "black3");
        assertTrue(be2.equals(be3));
    }

    @Test
    public void enrolledUserEntity()  {
        int i = eu1.getClassId();
        assertEquals(i, 1);
        String s = eu1.getUserEmail();
        assertEquals(s, "kjpetron@asu.edu");
        eu1.setClassId(2);
        i = eu1.getClassId();
        assertEquals(i, 2);
        eu1.setUserEmail("admin@gym.com");
        assertEquals(eu1.getUserEmail(), "admin@gym.com");
    }

    @Test
    public void gymClassEntity()  {
        assertEquals(1, gce1.getId());
        assertEquals(be1, gce1.getMinBeltEntityRequired());
        assertEquals("kjpetron@asu.edu", gce1.getTrainerEmail());
        assertEquals("admin@gym.com", gce1.getCreatedByEmail());
        assertEquals(1, gce1.getRoomNumber());
        assertEquals(10, gce1.getMaxClassSize());
        assertEquals(ldt1, gce1.getStartDateTime());
        assertEquals(ldt1, gce1.getEndDateTime());
        assertTrue(gce1.equals(gce1));
    }

    @Test
    public void userEntity()  {
    }


}
