/**
 * Project.java
 * Created on 11.02.2003, 16:11:47 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Project.
 */
/*$Id: Project.java,v 1.5 2004/11/22 10:02:37 alexeya Exp $*/
public interface Project {

    /**
     * The constant SCHEDULED.
     */
    public static final int SCHEDULED = 0;

    /**
     * The constant ACTIVE.
     */
    public static final int ACTIVE = 1;

    /**
     * The constant COMPLETED.
     */
    public static final int COMPLETED = 2;

    /**
     * The constant FROZEN.
     */
    public static final int FROZEN = 4;

    /**
     * The constant FAILED.
     */
    public static final int FAILED = 5;

    /**
     * Gets id.
     *
     * @return the id
     */
    String getID();

    /**
     * Gets start date.
     *
     * @return the start date
     */
    CalendarDate getStartDate();

    /**
     * Sets start date.
     *
     * @param date the date
     */
    void setStartDate(CalendarDate date);

    /**
     * Gets end date.
     *
     * @return the end date
     */
    CalendarDate getEndDate();

    /**
     * Sets end date.
     *
     * @param date the date
     */
    void setEndDate(CalendarDate date);

    /**
     * Gets title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Sets title.
     *
     * @param title the title
     */
    void setTitle(String title);

    /**
     * Sets description.
     *
     * @param description the description
     */
    void setDescription(String description);

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets status.
     *
     * @return the status
     */
    int getStatus();
            
    //int getProgress();
    
    //TaskList getTaskList();
    
    //NoteList getNoteList();
    
    //ResourcesList getResourcesList();

    /**
     * Freeze.
     */
    void freeze();

    /**
     * Unfreeze.
     */
    void unfreeze();
    
}
