/**
 * NoteList.java
 * Created on 21.02.2003, 15:40:46 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;
import java.util.Collection;

import main.java.memoranda.date.CalendarDate;

/**
 * The interface Note list.
 */
/*$Id: NoteList.java,v 1.5 2004/10/07 21:33:36 ivanrise Exp $*/
public interface NoteList {

    /**
     * Gets all notes.
     *
     * @return the all notes
     */
    Collection getAllNotes();

    /**
     * Gets marked notes.
     *
     * @return the marked notes
     */
    Collection getMarkedNotes();

    /**
     * Gets notes for period.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the notes for period
     */
    Collection getNotesForPeriod(CalendarDate startDate, CalendarDate endDate);

    /**
     * Gets note for date.
     *
     * @param date the date
     * @return the note for date
     */
    Note getNoteForDate(CalendarDate date);

    /**
     * Create note for date note.
     *
     * @param date the date
     * @return the note
     */
    Note createNoteForDate(CalendarDate date);

    /**
     * Remove note.
     *
     * @param date the date
     * @param id   the id
     */
//    void removeNoteForDate(CalendarDate date);
	void removeNote(CalendarDate date, String id);

    /**
     * Gets active note.
     *
     * @return the active note
     */
    Note getActiveNote();

    /**
     * Gets xml content.
     *
     * @return the xml content
     */
    nu.xom.Document getXMLContent();

}
