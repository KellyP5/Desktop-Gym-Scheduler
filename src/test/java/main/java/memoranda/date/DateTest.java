package test.java.main.java.memoranda.date;

import static org.junit.Assert.*;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;


public class DateTest {

    private CalendarDate test1;
    private CalendarDate test2;
    private CalendarDate test3;

    private CurrentDate test4;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {


        test1 = new CalendarDate(3,5,2020);
        test2 = new CalendarDate(2,5,2020);
        test3 = new CalendarDate(3,5,2020);
        test4 = new CurrentDate();



    }

    @Test
    public void equalsTest() {
        assertTrue(test1.equals(test3));
        assertFalse(test1.equals(test2));
        assertFalse(test4.equals(test3));
    }
}