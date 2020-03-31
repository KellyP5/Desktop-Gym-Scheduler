package main.java.memoranda;

/**
 * The interface Note listener.
 */
public interface NoteListener {
    /**
     * Note change.
     *
     * @param note              the note
     * @param toSaveCurrentNote the to save current note
     */
    void noteChange(Note note, boolean toSaveCurrentNote);
}
