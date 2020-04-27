/**
 * Util.java
 * Created on 11.02.2003, 23:59:21 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda team: http://memoranda.sf.net
 */
package main.java.memoranda.util;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JFileChooser;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.ExceptionDialog;

/**
 * The type Util.
 */
/*$Id: Util.java,v 1.13 2007/03/20 08:22:41 alexeya Exp $*/
public class Util {

    /**
     * The Seed.
     */
    static long seed = 0;

    /**
     * Generate id string.
     *
     * @return the string
     */
    public static String generateId() {
        long seed1 = System.currentTimeMillis();
        while (seed1 == seed) 
        	seed1 = System.currentTimeMillis(); // Make sure we'll don't get the same seed twice		  
    	seed = seed1;        	
    	Random r = new Random(seed); 
    	return Integer.toString(r.nextInt(), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16);
                    
    }

    /**
     * Gets date stamp.
     *
     * @param cal the cal
     * @return the date stamp
     */
    public static String getDateStamp(Calendar cal) {
        return cal.get(Calendar.DAY_OF_MONTH)
            + "/"
            + (cal.get(Calendar.MONTH))
            + "/"
            + new Integer(cal.get(Calendar.YEAR)).toString();

    }

    /**
     * Gets date stamp.
     *
     * @param date the date
     * @return the date stamp
     */
    public static String getDateStamp(CalendarDate date) {
        return Util.getDateStamp(date.getCalendar());
    }

    /**
     * Parse date stamp int [ ].
     *
     * @param s the s
     * @return the int [ ]
     */
    public static int[] parseDateStamp(String s) {
        s = s.trim();
        int i1 = s.indexOf("/");
        int i2 = s.indexOf("/", i1 + 1);
        int[] date = new int[3];
        date[0] = new Integer(s.substring(0, i1)).intValue();
        date[1] = new Integer(s.substring(i1 + 1, i2)).intValue();
        date[2] = new Integer(s.substring(i2 + 1)).intValue();
        return date;
        /*DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale);
        Date d = null;
        try {
            d = dateFormat.parse(s);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new int[3];
        }
        int[] ret = {d.getDay(), d.getMonth(), d.getYear()};
        return ret;*/
    }

    /**
     * Gets env dir.
     *
     * @return the env dir
     */
    public static String getEnvDir() {
    	// Changed static building of getEnvDir
    	// Now system-related path-separator is used
		String p = System.getProperty("user.home") + File.separator 
			+ ".jnotes2" + File.separator;
        if (new File(p).isDirectory()) return p;
        return System.getProperty("user.home") + File.separator 
        	+ ".memoranda" + File.separator;
    }

    /**
     * Gets cdata.
     *
     * @param s the s
     * @return the cdata
     */
    public static String getCDATA(String s) {
      return "<![CDATA["+s+"]]>";
    }

    /**
     * Run browser.
     *
     * @param url the url
     */
    public static void runBrowser(String url) {
        if (!checkBrowser())
            return;
        String commandLine = MimeTypesList.getAppList().getBrowserExec()+" "+url;
        System.out.println("Run: " + commandLine);
        try {
            /*DEBUG*/
            Runtime.getRuntime().exec(commandLine);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Failed to run an external web-browser application with commandline<br><code>"
                    +commandLine+"</code>", "Check the application path and command line parameters " +
                    		"(File-&gt;Preferences-&gt;Resource types).");
        }
    }

    /**
     * Check browser boolean.
     *
     * @return the boolean
     */
    public static boolean checkBrowser() {
        AppList appList = MimeTypesList.getAppList();
        String bpath = appList.getBrowserExec();
        if (bpath != null)
            if (new File(bpath).isFile())
                return true;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Select the web-browser executable"));
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        /*java.io.File lastSel = (java.io.File) Context.get("LAST_SELECTED_RESOURCE_FILE");
        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);*/
        if (chooser.showOpenDialog(App.getFrame()) != JFileChooser.APPROVE_OPTION)
            return false;
        appList.setBrowserExec(chooser.getSelectedFile().getPath());
        CurrentStorage.get().storeMimeTypesList();
        return true;
    }

    /**
     * Gets hours from millis.
     *
     * @param ms the ms
     * @return the hours from millis
     */
    public static String getHoursFromMillis(long ms) {
    	double numSeconds = (((double) ms) / 1000d);
    	return String.valueOf(numSeconds / 3600);
    }

    /**
     * Gets millis from hours.
     *
     * @param hours the hours
     * @return the millis from hours
     */
    public static long getMillisFromHours(String hours) {
    	try {
        	double numHours = Double.parseDouble(hours);
        	double millisDouble = (numHours * 3600 * 1000);
        	return (long) millisDouble;
    	}
    	catch (NumberFormatException e) {
    		return 0;
    	}
    }

    /**
     * The Temp files.
     */
    static Set tempFiles = new HashSet();
    
/*    static {
    	AppFrame.addExitListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (Iterator i = tempFiles.iterator(); i.hasNext();) 
					((File)i.next()).delete();				}
			});
    }*/

    /**
     * Gets temp file.
     *
     * @return the temp file
     * @throws IOException the io exception
     */
    public static File getTempFile() throws IOException {
    	File f = File.createTempFile("tmp", ".html", null);
    	tempFiles.add(f);
    	return f;
    }

    /**
     * Debug.
     *
     * @param str the str
     */
    public static void debug(String str) {
    	System.out.println("[DEBUG] " + str);
    }

    /**
     * Error.
     *
     * @param e the e
     */
    public static void error(Exception e) {
        System.out.println("[ERROR] Exception: " + e.getClass().getName());
        System.out.println("[ERROR] Exception Message: " + e.getMessage());
        
        String stackTrace = "";
        StackTraceElement[] ste = e.getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            stackTrace = ste[i].toString() + "\n";
        }
        System.out.println("[ERROR] Stack Trace: " + stackTrace);
    }
}
