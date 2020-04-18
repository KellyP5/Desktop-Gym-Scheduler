/**
 * Event.java
 * Created on 08.03.2003, 12:21:40 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 *-----------------------------------------------------
 */
package main.java.memoranda;
import java.util.Date;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Event.
 */
/*$Id: Event.java,v 1.4 2004/07/21 17:51:25 ivanrise Exp $*/
public interface Event {

    /**
     * Gets id.
     *
     * @return the id
     */
    String getId();
    
    //CalendarDate getDate();

    /**
     * Gets hour.
     *
     * @return the hour
     */
    int getHour();

    /**
     * Gets minute.
     *
     * @return the minute
     */
    int getMinute();
    
    //Date getTime();

    /**
     * Gets text.
     *
     * @return the text
     */
    String getText();

    /**
     * Gets content.
     *
     * @return the content
     */
    nu.xom.Element getContent();

    /**
     * Gets repeat.
     *
     * @return the repeat
     */
    int getRepeat();

    /**
     * Gets start date.
     *
     * @return the start date
     */
    CalendarDate getStartDate();

    /**
     * Gets end date.
     *
     * @return the end date
     */
    CalendarDate getEndDate();

    /**
     * Gets period.
     *
     * @return the period
     */
    int getPeriod();

    /**
     * Gets time.
     *
     * @return the time
     */
    Date getTime();

    /**
     * Gets time string.
     *
     * @return the time string
     */
    String getTimeString();

    /**
     * Gets working days.
     *
     * @return the working days
     */
    boolean getWorkingDays();
    
}
