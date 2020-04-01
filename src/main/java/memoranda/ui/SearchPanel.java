package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.text.Document;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Note;
import main.java.memoranda.NoteList;
import main.java.memoranda.Project;
import main.java.memoranda.ProjectListener;
import main.java.memoranda.ResourcesList;
import main.java.memoranda.TaskList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;

/**
 * The type Search panel.
 */
/*$Id: SearchPanel.java,v 1.5 2004/04/05 10:05:44 alexeya Exp $*/
public class SearchPanel extends JPanel {
    /**
     * The Border layout 1.
     */
    BorderLayout borderLayout1 = new BorderLayout();
    /**
     * The Notes list.
     */
    NotesList notesList = new NotesList(NotesList.EMPTY);
    /**
     * The Scroll pane.
     */
    JScrollPane scrollPane = new JScrollPane();
    /**
     * The J panel 1.
     */
    JPanel jPanel1 = new JPanel();
    /**
     * The Border layout 2.
     */
    BorderLayout borderLayout2 = new BorderLayout();
    /**
     * The Search field.
     */
    JTextField searchField = new JTextField();
    /**
     * The J panel 2.
     */
    JPanel jPanel2 = new JPanel();
    /**
     * The Border layout 3.
     */
    BorderLayout borderLayout3 = new BorderLayout();
    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Titled border 1.
     */
    TitledBorder titledBorder1;
    /**
     * The J panel 3.
     */
    JPanel jPanel3 = new JPanel();
    /**
     * The J panel 4.
     */
    JPanel jPanel4 = new JPanel();
    /**
     * The Case sens cb.
     */
    JCheckBox caseSensCB = new JCheckBox();
    /**
     * The Regexp cb.
     */
    JCheckBox regexpCB = new JCheckBox();
    /**
     * The Whole wcb.
     */
    JCheckBox wholeWCB = new JCheckBox();
    /**
     * The Search b.
     */
    JButton searchB = new JButton();
    /**
     * The Border layout 4.
     */
    BorderLayout borderLayout4 = new BorderLayout();
    /**
     * The Border layout 5.
     */
    BorderLayout borderLayout5 = new BorderLayout();
    /**
     * The Progress bar.
     */
    JProgressBar progressBar = new JProgressBar();

    /**
     * Instantiates a new Search panel.
     */
    public SearchPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Jb init.
     *
     * @throws Exception the exception
     */
    void jbInit() throws Exception {
        border1 = BorderFactory.createEmptyBorder(2, 2, 2, 2);

        titledBorder1 = new TitledBorder(BorderFactory.createEmptyBorder(), Local.getString("Search") + ":");

        this.setLayout(borderLayout1);

        jPanel1.setLayout(borderLayout2);
        jPanel2.setLayout(borderLayout3);
        jPanel2.setBorder(titledBorder1);
        titledBorder1.setTitleFont(new java.awt.Font("Dialog", 1, 11));
        searchField.setFont(new java.awt.Font("Dialog", 1, 10));
        searchField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                searchField_caretUpdate(e);
            }
        });
        jPanel3.setLayout(borderLayout5);
        caseSensCB.setText(Local.getString("Case sensitive"));
        caseSensCB.setFont(new java.awt.Font("Dialog", 1, 10));
        caseSensCB.setMargin(new Insets(0, 0, 0, 0));

        regexpCB.setFont(new java.awt.Font("Dialog", 1, 10));
        regexpCB.setMargin(new Insets(0, 0, 0, 0));
        regexpCB.setText(Local.getString("Regular expressions"));
        wholeWCB.setText(Local.getString("Whole words only"));
        wholeWCB.setMargin(new Insets(0, 0, 0, 0));
        wholeWCB.setFont(new java.awt.Font("Dialog", 1, 10));
        searchB.setEnabled(false);
        searchB.setFont(new java.awt.Font("Dialog", 1, 11));
        searchB.setMaximumSize(new Dimension(72, 25));
        searchB.setMinimumSize(new Dimension(2, 25));
        searchB.setPreferredSize(new Dimension(70, 25));
        searchB.setMargin(new Insets(0, 0, 0, 0));
        searchB.setText(Local.getString("Search"));
        searchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchB_actionPerformed(e);
            }
        });
        jPanel4.setLayout(borderLayout4);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(jPanel1, BorderLayout.NORTH);
        scrollPane.getViewport().add(notesList);
        jPanel1.add(jPanel2, BorderLayout.NORTH);
        jPanel2.add(searchField, BorderLayout.CENTER);
        jPanel1.add(jPanel3, BorderLayout.CENTER);
        jPanel3.add(jPanel4, BorderLayout.NORTH);
        jPanel4.add(caseSensCB, BorderLayout.SOUTH);
        jPanel4.add(wholeWCB, BorderLayout.NORTH);
        jPanel4.add(regexpCB, BorderLayout.CENTER);
        jPanel3.add(searchB, BorderLayout.SOUTH);
        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
                notesList.update(new Vector());
            }
            public void projectWasChanged() {}
        });
        //notesList.update(new Vector());

    }

    /**
     * The Wait cursor.
     */
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    /**
     * Search b action performed.
     *
     * @param e the e
     */
    void searchB_actionPerformed(ActionEvent e) {
        Cursor cur = App.getFrame().getCursor();
        App.getFrame().setCursor(waitCursor);
        doSearch();
        App.getFrame().setCursor(cur);
    }

    /**
     * Search field caret update.
     *
     * @param e the e
     */
    void searchField_caretUpdate(CaretEvent e) {
        searchB.setEnabled(searchField.getText().length() > 0);
    }


    /**
     * Do search.
     */
    void doSearch() {
        Pattern pattern;
        //this.add(progressBar, BorderLayout.SOUTH);
        int flags = Pattern.DOTALL;
        if (!caseSensCB.isSelected())
            flags = flags + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE;
        String _find = searchField.getText();
        if (!regexpCB.isSelected())
            _find = "\\Q" + _find + "\\E";
        if (wholeWCB.isSelected())
            _find = "[\\s\\p{Punct}]" + _find + "[\\s\\p{Punct}]";
        try {
            pattern = Pattern.compile(_find, flags);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Error in regular expression", "Check the regular expression syntax");
            return;
        }
        /*progressBar.setMinimum(0);
        progressBar.setStringPainted(true);*/
        Vector notes = (Vector) CurrentProject.getNoteList().getAllNotes();
        Vector found = new Vector();
        /*progressBar.setMaximum(notes.size()-1);
        progressBar.setIndeterminate(false);
        this.add(progressBar, BorderLayout.SOUTH);*/
        for (int i = 0; i < notes.size(); i++) {
            //progressBar.setValue(i);
            Note note = (Note) notes.get(i);
            Document doc = CurrentStorage.get().openNote(note);
            try {
                String txt = doc.getText(0, doc.getLength());
                Matcher matcher = pattern.matcher(txt);
                if (matcher.find())
                    found.add(note);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //this.remove(progressBar);
        this.notesList.update(found);
    }

}