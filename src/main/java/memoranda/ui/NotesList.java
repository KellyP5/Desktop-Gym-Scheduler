package main.java.memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import main.java.memoranda.CurrentNote;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.Note;
import main.java.memoranda.NoteList;
import main.java.memoranda.NoteListener;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Configuration;

/**
 * The type Notes list.
 */
/*$Id: NotesList.java,v 1.9 2005/05/05 16:19:16 ivanrise Exp $*/
public class NotesList extends JList {

    /**
     * The constant EMPTY.
     */
    public static final int EMPTY = 0;
    /**
     * The constant ALL.
     */
    public static final int ALL = 1;
    /**
     * The constant BOOKMARKS.
     */
    public static final int BOOKMARKS = 2;

    private Vector notes = null;
    /**
     * The Sort order desc.
     */
    boolean sortOrderDesc = false;

    /**
     * The Type.
     */
    int _type = ALL;

    /**
     * Instantiates a new Notes list.
     *
     * @param type the type
     */
    public NotesList(int type) {
        super();
		if(Configuration.get("NOTES_SORT_ORDER").toString().equalsIgnoreCase("true")) {
			sortOrderDesc = true;
		}
        _type = type;
        this.setFont(new java.awt.Font("Dialog", 0, 11));
        this.setModel(new NotesListModel());
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                updateUI();
            }
        });
		
        CurrentNote.addNoteListener(new NoteListener() {
            public void noteChange(Note n, boolean toSaveCurrentNote) {
                updateUI();
            }
        });


        this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    /**
     * Instantiates a new Notes list.
     */
    public NotesList() {
        this(ALL);
    }

    /**
     * Update.
     */
    public void update() {
        if (_type != EMPTY) {
            update(CurrentProject.getNoteList());
		}
        else {
			update(new Vector());
		}
    }

    /**
     * Update.
     *
     * @param nl the nl
     */
    public void update(NoteList nl) {
        if (_type == ALL)
            notes = (Vector) nl.getAllNotes();
        else
            notes = (Vector) nl.getMarkedNotes();
        
//        Util.debug("No. of notes in noteList " + notes.size());
        //NotesVectorSorter.sort(notes);
		Collections.sort(notes);
		if (sortOrderDesc) {
			Collections.reverse(notes);		    
		}
        updateUI();
    }

    /**
     * Update.
     *
     * @param ns the ns
     */
    public void update(Vector ns) {
        notes = ns;
        // NotesVectorSorter.sort(notes);
		Collections.sort(notes);
		if (sortOrderDesc) {
			Collections.reverse(notes);		    
		}		
        updateUI();
    }

    /**
     * Get note note.
     *
     * @param index the index
     * @return the note
     */
    public Note getNote(int index){
        return (Note) notes.get(index);
    }

    /**
     * Invert sort order.
     */
    void invertSortOrder() {
        sortOrderDesc = !sortOrderDesc;
    }


    /**
     * The type Notes list model.
     */
    /*$Id: NotesList.java,v 1.9 2005/05/05 16:19:16 ivanrise Exp $*/
public class NotesListModel extends AbstractListModel {

        /**
         * Instantiates a new Notes list model.
         */
        public NotesListModel() {
            update();
        }

        public Object getElementAt(int i) {
            Note note = (Note)notes.get(i);
            return note.getDate().getShortDateString() + " " + note.getTitle();
        }

        public int getSize() {
            return notes.size();
        }

    }

    /**
     * The Bookmark icon.
     */
    ImageIcon bookmarkIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/star8.png"));

    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer()  {

     public Component getListCellRendererComponent(
       JList list,
       Object value,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // the list and the cell have the focus
     {
         JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         String s = value.toString();
         label.setText(s);
         //Note currentNote = CurrentProject.getNoteList().getActiveNote();
		 Note currentNote = CurrentNote.get();
         if (currentNote != null) {
            if (getNote(index).getId().equals(currentNote.getId()))
                label.setFont(label.getFont().deriveFont(Font.BOLD));
         }
         if (getNote(index).isMarked())
            label.setIcon(bookmarkIcon);
         //setIcon();
       /*if (isSelected) {
             setBackground(list.getSelectionBackground());
           setForeground(list.getSelectionForeground());
       }
         else {
           setBackground(list.getBackground());
           setForeground(list.getForeground());
       }
       setEnabled(list.isEnabled());
       setFont(list.getFont());
         setOpaque(true);*/
         label.setToolTipText(s);
         return label;
     }
    };

 }


}