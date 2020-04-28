/**
 * Storage.java
 * Created on 12.02.2003, 0:58:42 Alex
 * Package: net.sf.memoranda.util
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;

import main.java.memoranda.Note;
import main.java.memoranda.NoteList;
import main.java.memoranda.Project;


/**
 * The interface Storage.
 */
/*$Id: Storage.java,v 1.4 2004/01/30 12:17:42 alexeya Exp $*/
public interface Storage {


    /**
     * Open note list note list.
     *
     * @param prj the prj
     * @return the note list
     */
    NoteList openNoteList(Project prj);

    /**
     * Store note list.
     *
     * @param nl  the nl
     * @param prj the prj
     */
    void storeNoteList(NoteList nl, Project prj);

    /**
     * Store note.
     *
     * @param note the note
     * @param doc  the doc
     */
    void storeNote(Note note, javax.swing.text.Document doc);

    /**
     * Open note javax . swing . text . document.
     *
     * @param note the note
     * @return the javax . swing . text . document
     */
    javax.swing.text.Document openNote(Note note);

    /**
     * Remove note.
     *
     * @param note the note
     */
    void removeNote(Note note);

    /**
     * Gets note url.
     *
     * @param note the note
     * @return the note url
     */
    String getNoteURL(Note note);

    /**
     * Open project manager.
     */
    void openProjectManager();

    /**
     * Store project manager.
     */
    void storeProjectManager();

    /**
     * Open events manager.
     */
    void openEventsManager();

    /**
     * Store events manager.
     */
    void storeEventsManager();

    /**
     * Open mime types list.
     */
    void openMimeTypesList();

    /**
     * Store mime types list.
     */
    void storeMimeTypesList();

    /**
     * Create project storage.
     *
     * @param prj the prj
     */
    void createProjectStorage(Project prj);

    /**
     * Remove project storage.
     *
     * @param prj the prj
     */
    void removeProjectStorage(Project prj);


    /**
     * Restore context.
     */
    void restoreContext();

    /**
     * Store context.
     */
    void storeContext();
       
}
