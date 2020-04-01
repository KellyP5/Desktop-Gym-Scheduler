/**
 * Task.java
 * Created on 11.02.2003, 16:39:13 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import java.util.Collection;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Task.
 */
/*$Id: Task.java,v 1.9 2005/06/16 04:21:32 alexeya Exp $*/
public interface Task {

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
     * The constant LOCKED.
     */
    public static final int LOCKED = 6;

    /**
     * The constant DEADLINE.
     */
    public static final int DEADLINE = 7;

    /**
     * The constant PRIORITY_LOWEST.
     */
    public static final int PRIORITY_LOWEST = 0;

    /**
     * The constant PRIORITY_LOW.
     */
    public static final int PRIORITY_LOW = 1;

    /**
     * The constant PRIORITY_NORMAL.
     */
    public static final int PRIORITY_NORMAL = 2;

    /**
     * The constant PRIORITY_HIGH.
     */
    public static final int PRIORITY_HIGH = 3;

    /**
     * The constant PRIORITY_HIGHEST.
     */
    public static final int PRIORITY_HIGHEST = 4;

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
     * Gets status.
     *
     * @param date the date
     * @return the status
     */
    int getStatus(CalendarDate date);

    /**
     * Gets progress.
     *
     * @return the progress
     */
    int getProgress();

    /**
     * Sets progress.
     *
     * @param p the p
     */
    void setProgress(int p);

    /**
     * Gets priority.
     *
     * @return the priority
     */
    int getPriority();

    /**
     * Sets priority.
     *
     * @param p the p
     */
    void setPriority(int p);

    /**
     * Gets id.
     *
     * @return the id
     */
    String getID();

    /**
     * Gets text.
     *
     * @return the text
     */
    String getText();

    /**
     * Sets text.
     *
     * @param s the s
     */
    void setText(String s);
    
    /*Collection getDependsFrom();
    
    void addDependsFrom(Task task);
    
    void removeDependsFrom(Task task);*/

    /**
     * Gets sub tasks.
     *
     * @return the sub tasks
     */
    Collection getSubTasks();

    /**
     * Gets sub task.
     *
     * @param id the id
     * @return the sub task
     */
    Task getSubTask(String id);

    /**
     * Has sub tasks boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean hasSubTasks(String id);

    /**
     * Sets effort.
     *
     * @param effort the effort
     */
    void setEffort(long effort);

    /**
     * Gets effort.
     *
     * @return the effort
     */
    long getEffort();

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
     * Gets parent task.
     *
     * @return the parent task
     */
    Task getParentTask();

    /**
     * Gets parent id.
     *
     * @return the parent id
     */
    String getParentId();

    /**
     * Freeze.
     */
    void freeze();

    /**
     * Unfreeze.
     */
    void unfreeze();

    /**
     * Gets rate.
     *
     * @return the rate
     */
    long getRate();

    /**
     * Gets content.
     *
     * @return the content
     */
    nu.xom.Element getContent();
}
