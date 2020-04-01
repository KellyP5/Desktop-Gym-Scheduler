/**
 * HistoryItem.java
 * Created on 07.03.2003, 18:31:39 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;

/**
 * The type History item.
 */
/*$Id: HistoryItem.java,v 1.4 2004/10/06 19:15:43 ivanrise Exp $*/
public class HistoryItem {
    
    private CalendarDate _date;
    private Project _project;

    /**
     * Constructor for HistoryItem.
     *
     * @param date    the date
     * @param project the project
     */
    public HistoryItem(CalendarDate date, Project project) {
        _date = date;
        _project = project;
    }

    /**
     * Instantiates a new History item.
     *
     * @param note the note
     */
    public HistoryItem(Note note) {
        _date = note.getDate();
        _project = note.getProject();
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public CalendarDate getDate() {
       return _date;
    }

    /**
     * Gets project.
     *
     * @return the project
     */
    public Project getProject() {
       return _project;
    }

    /**
     * Equals boolean.
     *
     * @param i the
     * @return the boolean
     */
    public boolean equals(HistoryItem i) {
       return i.getDate().equals(_date) && i.getProject().getID().equals(_project.getID());
    } 

}
