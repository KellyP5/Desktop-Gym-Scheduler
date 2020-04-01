/**
 * TaskList.java
 * Created on 21.02.2003, 12:25:16 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;
import java.util.Collection;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Task list.
 */
/*$Id: TaskList.java,v 1.8 2005/12/01 08:12:26 alexeya Exp $*/
public interface TaskList {

    /**
     * Gets project.
     *
     * @return the project
     */
    Project getProject();

    /**
     * Gets task.
     *
     * @param id the id
     * @return the task
     */
    Task getTask(String id);

    /**
     * Create task task.
     *
     * @param startDate    the start date
     * @param endDate      the end date
     * @param text         the text
     * @param priority     the priority
     * @param effort       the effort
     * @param description  the description
     * @param parentTaskId the parent task id
     * @return the task
     */
    Task createTask(CalendarDate startDate, CalendarDate endDate, String text, int priority, long effort, String description, String parentTaskId);

    /**
     * Remove task.
     *
     * @param task the task
     */
    void removeTask(Task task);

    /**
     * Has sub tasks boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean hasSubTasks(String id);

    /**
     * Has parent task boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean hasParentTask(String id);

    /**
     * Gets top level tasks.
     *
     * @return the top level tasks
     */
    public Collection getTopLevelTasks();

    /**
     * Gets all sub tasks.
     *
     * @param taskId the task id
     * @return the all sub tasks
     */
    public Collection getAllSubTasks(String taskId);

    /**
     * Gets active sub tasks.
     *
     * @param taskId the task id
     * @param date   the date
     * @return the active sub tasks
     */
    public Collection getActiveSubTasks(String taskId,CalendarDate date);
    
//    public void adjustParentTasks(Task t);

    /**
     * Calculate total effort from sub tasks long.
     *
     * @param t the t
     * @return the long
     */
    public long calculateTotalEffortFromSubTasks(Task t);

    /**
     * Gets latest end date from sub tasks.
     *
     * @param t the t
     * @return the latest end date from sub tasks
     */
    public CalendarDate getLatestEndDateFromSubTasks(Task t);

    /**
     * Gets earliest start date from sub tasks.
     *
     * @param t the t
     * @return the earliest start date from sub tasks
     */
    public CalendarDate getEarliestStartDateFromSubTasks(Task t);

    /**
     * Calculate completion from sub tasks long [ ].
     *
     * @param t the t
     * @return the long [ ]
     */
    public long[] calculateCompletionFromSubTasks(Task t);

    /**
     * Gets xml content.
     *
     * @return the xml content
     */
    nu.xom.Document getXMLContent();

}
