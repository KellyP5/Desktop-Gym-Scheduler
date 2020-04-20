package test.java;


import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import org.junit.*;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * Tests CurrentDate class
 */
public class CurrentDateTest {
    static CurrentDate cd1, cd2;
    static CalendarDate calDate1, calDate2;


    /**
     * Sets up for current date tests
     */
    @BeforeClass
    public static void setUp() {
        cd1 = new CurrentDate();
        cd2 = new CurrentDate();


    }

    /**
     * Tests set and reset methods
     */
    @Test
    public void currentDateSetAndReset() {
        calDate1 = new CalendarDate(19, 4, 2020);
        cd1.set(calDate1);
        assertEquals(calDate1, cd1.get());
        cd1.reset();
        assertNotSame(calDate1, cd1.get());

    }







}
