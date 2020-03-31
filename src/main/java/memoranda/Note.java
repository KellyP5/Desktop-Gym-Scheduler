/**
 * Note.java
 * Created on 11.02.2003, 17:05:27 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Note.
 */
/*$Id: Note.java,v 1.4 2004/09/30 17:19:52 ivanrise Exp $*/
public interface Note {

    /**
     * Gets date.
     *
     * @return the date
     */
    CalendarDate getDate();

    /**
     * Gets title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Sets title.
     *
     * @param s the s
     */
    void setTitle(String s);

    /**
     * Gets id.
     *
     * @return the id
     */
    String getId();

    /**
     * Sets id.
     *
     * @param s the s
     */
    void setId(String s);

    /**
     * Is marked boolean.
     *
     * @return the boolean
     */
    boolean isMarked();

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    void setMark(boolean mark);

    /**
     * Gets project.
     *
     * @return the project
     */
    Project getProject();
}
