package main.java.memoranda.util;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;

import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.UserEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.App;

import java.io.*;

/**
 * Provides locale info
 */
/*$Id: Local.java,v 1.6 2004/10/11 08:48:21 alexeya Exp $*/
public class Local {

    /**
     * The Current locale.
     */
    static Locale currentLocale = Locale.getDefault();
    /**
     * The Messages.
     */
    static LoadableProperties messages = new LoadableProperties();
    /**
     * The Disabled.
     */
    static boolean disabled = false;

    static {
    	if (!Configuration.get("DISABLE_L10N").equals("yes")) {
	    	String fn = "messages_"
	                    + currentLocale.getLanguage()
	                    + ".properties";
	        if (Configuration.get("LOCALES_DIR") != "") {
	        	System.out.print("Look "+fn+" at: "+Configuration.get("LOCALES_DIR")+" ");
	        	try {
	        		messages.load(new FileInputStream(
	        			Configuration.get("LOCALES_DIR")+File.separator+fn));
	        		System.out.println(" - found");
	        	}
	        	catch (IOException ex) {
	        		// Do nothing ...
	        		System.out.println(" - not found");
	        		ex.printStackTrace();
	        	}
	        }
	        if (messages.size() == 0) {
		        try {
		            messages.load(
		                Local.class.getResourceAsStream(
		                    "localmessages/"+fn));            
		        }
		        catch (Exception e) {
		            // Do nothing ...
		        }
	        }
    	}
    	else {
    		currentLocale = new Locale("en", "US");
    		/*DEBUG*/
    		System.out.println("* DEBUG: Locales are disabled");
    	}       
    	if (messages.size() == 0) 
    		messages = null;
    		
        /*** DEBUG PURPOSES ***/
        System.out.println("Default locale: " + currentLocale.getDisplayName());
        if (messages != null) {
            System.out.println(
                "Use local messages: messages_"
                    + currentLocale.getLanguage()
                    + ".properties");
        }
        else {
            System.out.println(
                "* DEBUG: Locales are disabled or not found: messages_"
                    + currentLocale.getLanguage()
                    + ".properties");
        }        
        /**********************/
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public static Hashtable getMessages() {
        return messages;
    }

    /**
     * Gets current locale.
     *
     * @return the current locale
     */
    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * The Monthnames.
     */
    static String monthnames[] =
        {
            "Jan",
            "Feb",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December" };

    /**
     * The Weekdaynames.
     */
    static String weekdaynames[] =
        { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

    /**
     * The Beltnames.
     */
    static String beltnames[] =
            { "white", "yellow", "orange", "purple", "blue", "blue_stripe", "green", "green_stripe", "brown1", "brown2", "brown3", "black1", "black2", "black3"};

    static String classSize[] =
            {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     */
    public static String getString(String key) {
        if ((messages == null) || (disabled)) {
            return key;
        }
        String msg = (String) messages.get(key.trim().toUpperCase());
        if ((msg != null) && (msg.length() > 0)) {
            return msg;
        }
        else {
            return key;
        }
    }

    /**
     * Get month names string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getMonthNames() {
        String[] localmonthnames = new String[12];
        for (int i = 0; i < 12; i++) {
            localmonthnames[i] = getString(monthnames[i]);
        }
        return localmonthnames;
    }

    /**
     * Get belt names string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getBeltNames() {
        return beltnames;
    }

    public static String[] getMaxClassSize() {
        return classSize;
    }

    public static String[] getTrainerNames() {
        RoleEntity role = new RoleEntity("trainer");
        try {
            ArrayList<UserEntity> al = App.conn.getDrq().getAllUsersOfCertainRole(role);
            String[] trainers = new String[al.size()];
            for (int i=0; i < al.size(); i++) {
                trainers[i] = al.get(i).getEmail() + " Belt: " + al.get(i).getBelt().toString();
            }
            return trainers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get room names string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getRoomNames() {
        return roomnames;
    }

    public static String[] getTimes() {
        String[] times = new String[]{"0500", "0600", "0700", "0800", "0900",
        "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700", "1800"
        , "1900", "2000", "2100"};
        return times;
    }

    public static double getDoubleTime(String s) {
        String substr = s.substring(0, 2);
        double d = Double.valueOf(substr);
        return d;
    }

    /**
     * The Roomnames.
     */
    static String roomnames[] =
            { "Room 1", "Room 2", "Room 3", "Room 4"};

    /**
     * Get weekday names string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getWeekdayNames() {
        String[] localwdnames = new String[7];
        String[] localnames = weekdaynames;

        if (Configuration.get("FIRST_DAY_OF_WEEK").equals("mon"))
            localnames =
                new String[] {
                    "Mon",
                    "Tue",
                    "Wed",
                    "Thu",
                    "Fri",
                    "Sat",
                    "Sun" };

        for (int i = 0; i < 7; i++) {
            localwdnames[i] = getString(localnames[i]);
        }
        return localwdnames;
    }

    /**
     * Gets month name.
     *
     * @param m the m
     * @return the month name
     */
    public static String getMonthName(int m) {
        return getString(monthnames[m]);
    }

    /**
     * Gets weekday name.
     *
     * @param wd the wd
     * @return the weekday name
     */
    public static String getWeekdayName(int wd) {
        return getString(weekdaynames[wd - 1]);
    }

    /**
     * Gets date string.
     *
     * @param d the d
     * @param f the f
     * @return the date string
     */
    public static String getDateString(Date d, int f) {
        DateFormat dateFormat = DateFormat.getDateInstance(f, currentLocale);
        return dateFormat.format(d);
    }

    /**
     * Gets date string.
     *
     * @param cal the cal
     * @param f   the f
     * @return the date string
     */
    public static String getDateString(Calendar cal, int f) {
        /*@todo: Get date string format from locale*/
        /*String s =
            getMonthName(cal.get(Calendar.MONTH))
                + " "
                + cal.get(Calendar.DAY_OF_MONTH)
                + ", "
                + cal.get(Calendar.YEAR)
                + " ("
                + getWeekdayName(cal.get(Calendar.DAY_OF_WEEK))
                + ")";
        return s;*/
        return getDateString(cal.getTime(), f);
    }

    /**
     * Gets date string.
     *
     * @param date the date
     * @param f    the f
     * @return the date string
     */
    public static String getDateString(CalendarDate date, int f) {
        return getDateString(date.getDate(), f);
    }

    /**
     * Gets date string.
     *
     * @param m the m
     * @param d the d
     * @param y the y
     * @param f the f
     * @return the date string
     */
    public static String getDateString(int m, int d, int y, int f) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DAY_OF_MONTH, d);
        cal.set(Calendar.YEAR, y);

        //String s = getMonthName(m) + " " + d + ", " + y + " (" + getWeekdayName(cal.get(Calendar.DAY_OF_WEEK)) + ")";
        return getDateString(cal.getTime(), f);
    }

    /**
     * Gets time string.
     *
     * @param d the d
     * @return the time string
     */
    public static String getTimeString(Date d) {
        DateFormat dateFormat =
            DateFormat.getTimeInstance(DateFormat.SHORT, currentLocale);
        return dateFormat.format(d);
    }

    /**
     * Gets time string.
     *
     * @param cal the cal
     * @return the time string
     */
    public static String getTimeString(Calendar cal) {
        /*String h = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        if (h.length() < 2) {
            h = "0" + h;
        }
        String m = String.valueOf(cal.get(Calendar.MINUTE));
        if (m.length() < 2) {
            m = "0" + m;
        }
        return h + ":" + m;*/
        return getTimeString(cal.getTime());
    }

    /**
     * Gets time string.
     *
     * @param hh the hh
     * @param mm the mm
     * @return the time string
     */
    public static String getTimeString(int hh, int mm) {
        /*String h = String.valueOf(hh);
        if (h.length() < 2) {
            h = "0" + h;
        }
        String m = String.valueOf(mm);
        if (m.length() < 2) {
            m = "0" + m;
        }
        return h + ":" + m;*/
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mm);
        return getTimeString(cal.getTime());
    }

    /**
     * Parse time string int [ ].
     *
     * @param s the s
     * @return the int [ ]
     */
    public static int[] parseTimeString(String s) {
        s = s.trim();
        String h = "";
        String m = "";
        if (s.indexOf(":") > 0) {
            h = s.substring(0, s.indexOf(":"));
            m = s.substring(s.indexOf(":") + 1);
        }
        else if (s.indexOf(":") == 0) {
            h = "0";
            m = s;
        }
        else {
            h = s;
            m = "0";
        }
        int[] time = new int[2];
        try {
            time[0] = new Integer(h).intValue();
            if ((time[0] < 0) || (time[0] > 23)) {
                time[0] = 0;
            }
        }
        catch (NumberFormatException nfe) {
            return null;
        }
        try {
            time[1] = new Integer(m).intValue();
            if ((time[1] < 0) || (time[1] > 59)) {
                time[1] = 0;
            }
        }
        catch (NumberFormatException nfe) {
            return null;
        }
        return time;
    }

}
