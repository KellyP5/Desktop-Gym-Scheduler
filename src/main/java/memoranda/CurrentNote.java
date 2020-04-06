package main.java.memoranda;

import java.util.Collection;
import java.util.Vector;

/**
 * The type Current note.
 */
public class CurrentNote {

	private static Note currentNote = null;
    private static Vector noteListeners = new Vector();

    /**
     * Get note.
     *
     * @return the note
     */
    public static Note get() {
        return currentNote;
    }

    /**
     * Set.
     *
     * @param note              the note
     * @param toSaveCurrentNote the to save current note
     */
    public static void set(Note note, boolean toSaveCurrentNote) {
        noteChanged(note, toSaveCurrentNote);
        currentNote = note;
    }

    /**
     * Reset.
     */
    public static void reset() {
//    	 set toSave to true to mimic status quo behaviour only. the appropriate setting could be false
        set(null, true);
    }

    /**
     * Add note listener.
     *
     * @param nl the nl
     */
    public static void addNoteListener(NoteListener nl) {
        noteListeners.add(nl);
    }

    /**
     * Gets change listeners.
     *
     * @return the change listeners
     */
    public static Collection getChangeListeners() {
        return noteListeners;
    }

    private static void noteChanged(Note note, boolean toSaveCurrentNote) {
        for (int i = 0; i < noteListeners.size(); i++) {
            ((NoteListener)noteListeners.get(i)).noteChange(note,toSaveCurrentNote);
		}
    }
}
