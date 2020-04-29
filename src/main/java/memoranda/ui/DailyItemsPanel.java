package main.java.memoranda.ui;

import main.java.memoranda.*;
import main.java.memoranda.database.GymClassEntity;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import main.java.memoranda.CurrentNote;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.History;
import main.java.memoranda.HistoryItem;
import main.java.memoranda.HistoryListener;
import main.java.memoranda.Note;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.classes.ClassesPanel;

/**
 * DailyItemsPanel is the panel everyone sees.
 *
 * This class is reused in Agenda, Classes, Tasks, Notes tabs on the left of Globogym
 */

public class DailyItemsPanel extends JPanel {

    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();       //Used by statusPanel
    BorderLayout borderLayout5 = new BorderLayout();

    CardLayout cardLayout1 = new CardLayout();

    JSplitPane splitPane = new JSplitPane();

    JPanel controlPanel = new JPanel();     //Holds the calender on the left hand side
    JPanel mainPanel = new JPanel();

    JPanel statusPanel = new JPanel();      //The top bar that displays the current day with black background
    JLabel currentDateLabel = new JLabel(); //The label that goes into the status panel.

    JPanel editorsPanel = new JPanel();


    ClassesPanel classesPanel = null;
    AgendaPanel agendaPanel = null;

    ImageIcon expIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_right.png"));
    ImageIcon collIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_left.png"));
    ImageIcon bookmarkIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/star8.png"));

    boolean expanded = true;

    public CalendarDate currentDate;
    private GymClassEntity selectedClass;

    boolean calendarIgnoreChange = false;
    boolean dateChangedByCalendar = false;
    boolean changedByHistory = false;
    JPanel cmainPanel = new JPanel();
    JNCalendarPanel calendar = new JNCalendarPanel();

    JToolBar toggleToolBar = new JToolBar();



    Border border1;

    JButton toggleButton = new JButton();

    WorkPanel parentPanel = null;

    boolean addedToHistory = false;

    JPanel indicatorsPanel = new JPanel();

    FlowLayout flowLayout1 = new FlowLayout();


    JPanel mainTabsPanel = new JPanel();

    NotesControlPanel notesControlPane = new NotesControlPanel();

    CardLayout cardLayout2 = new CardLayout();

    Border border2;

    String CurrentPanel;//the current panel name that is being viewed

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    public EditorPanel editorPanel = new EditorPanel(this);




    Note currentNote;

    /**
     * Instantiates a new Daily items panel.
     *
     * @param _parentPanel the parent panel
     */
    public DailyItemsPanel(WorkPanel _parentPanel) {
        try {

            parentPanel = _parentPanel;
            currentDate = CurrentDate.get();
            classesPanel = new ClassesPanel(this);
            agendaPanel = new AgendaPanel(this);

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
        border1 = BorderFactory.createEtchedBorder(Color.white, Color.gray);
        border2 = BorderFactory.createEtchedBorder(Color.white, new Color(161, 161, 161));
        this.setLayout(borderLayout1);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBorder(null);
        splitPane.setDividerSize(2);
        controlPanel.setLayout(borderLayout2);
        //calendar.setMinimumSize(new Dimension(200, 170));
        mainPanel.setLayout(borderLayout3);
        editorsPanel.setLayout(cardLayout1);
        statusPanel.setBackground(Color.black);
        statusPanel.setForeground(Color.white);
        statusPanel.setMinimumSize(new Dimension(14, 24));
        statusPanel.setPreferredSize(new Dimension(14, 24));
        statusPanel.setLayout(borderLayout4);
        currentDateLabel.setFont(new java.awt.Font("Dialog", 0, 16));
        currentDateLabel.setForeground(Color.white);
        currentDateLabel.setText(CurrentDate.get().getFullDateString());
        borderLayout4.setHgap(4);
        controlPanel.setBackground(new Color(230, 230, 230));
        controlPanel.setBorder(border2);
        controlPanel.setMinimumSize(new Dimension(20, 170));
        controlPanel.setPreferredSize(new Dimension(205, 170));
        //controlPanel.setMaximumSize(new Dimension(206, 170));
        //controlPanel.setSize(controlPanel.getMaximumSize());
        calendar.setFont(new java.awt.Font("Dialog", 0, 11));
        calendar.setMinimumSize(new Dimension(0, 168));
        toggleToolBar.setBackground(new Color(215, 225, 250));
        toggleToolBar.setRequestFocusEnabled(false);
        toggleToolBar.setFloatable(false);
        cmainPanel.setLayout(borderLayout5);
        cmainPanel.setBackground(SystemColor.desktop);
        cmainPanel.setMinimumSize(new Dimension(0, 168));
        cmainPanel.setOpaque(false);
        toggleButton.setMaximumSize(new Dimension(32767, 32767));
        toggleButton.setMinimumSize(new Dimension(16, 16));
        toggleButton.setOpaque(false);
        toggleButton.setPreferredSize(new Dimension(16, 16));
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setMargin(new Insets(0, 0, 0, 0));
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleButton_actionPerformed(e);
            }
        });
        toggleButton.setIcon(collIcon);
        indicatorsPanel.setOpaque(false);
        indicatorsPanel.setLayout(flowLayout1);

        flowLayout1.setAlignment(FlowLayout.RIGHT);
        flowLayout1.setVgap(0);


        notesControlPane.setFont(new java.awt.Font("Dialog", 1, 10));
        mainTabsPanel.setLayout(cardLayout2);
        this.add(splitPane, BorderLayout.CENTER);

        controlPanel.add(cmainPanel, BorderLayout.CENTER);
        cmainPanel.add(calendar, BorderLayout.NORTH);

        mainPanel.add(statusPanel, BorderLayout.NORTH);
        statusPanel.add(currentDateLabel, BorderLayout.CENTER);
        statusPanel.add(indicatorsPanel, BorderLayout.EAST);

        mainPanel.add(editorsPanel, BorderLayout.CENTER);

        editorsPanel.add(agendaPanel, "AGENDA");
        editorsPanel.add(classesPanel, "CLASSES");
        editorsPanel.add(editorPanel, "NOTES");

        splitPane.add(mainPanel, JSplitPane.RIGHT);
        splitPane.add(controlPanel, JSplitPane.LEFT);
        controlPanel.add(toggleToolBar, BorderLayout.SOUTH);
        toggleToolBar.add(toggleButton, null);

        splitPane.setDividerLocation((int) controlPanel.getPreferredSize().getWidth());
        //splitPane.setResizeWeight(0.0);

        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                System.out.println("Debug: public void dateChange(CalendarDate d), d = " +d.getFullDateString());
                currentDateChanged(d);
            }
        });





        calendar.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (calendarIgnoreChange)
                    return;
                dateChangedByCalendar = true;
                try {
                    CurrentDate.set(calendar.get());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dateChangedByCalendar = false;
            }
        });



        History.addHistoryListener(new HistoryListener() {
            public void historyWasRolledTo(HistoryItem hi) throws SQLException {
                historyChanged(hi);
            }
        });



        currentNote = CurrentProject.getNoteList().getNoteForDate(CurrentDate.get());
        CurrentNote.set(currentNote,true);
        editorPanel.setDocument(currentNote);
        History.add(new HistoryItem(CurrentDate.get(), CurrentProject.get()));
        cmainPanel.add(mainTabsPanel, BorderLayout.CENTER);
        mainPanel.setBorder(null);
    }


    /**
     * Current date changed.
     *
     * @param newdate the newdate
     */
    void currentDateChanged(CalendarDate newdate) {
        Cursor cur = App.getFrame().getCursor();
        App.getFrame().setCursor(waitCursor);
        if (!changedByHistory) {
            History.add(new HistoryItem(newdate, CurrentProject.get()));
        }
        if (!dateChangedByCalendar) {
            calendarIgnoreChange = true;
            calendar.set(newdate);
            calendarIgnoreChange = false;
        }

        /*if ((currentNote != null) && !changedByHistory && !addedToHistory)
                            History.add(new HistoryItem(currentNote));*/

        currentNote = CurrentProject.getNoteList().getNoteForDate(newdate);
        CurrentNote.set(currentNote,true);
        currentDate = CurrentDate.get();

        /*addedToHistory = false;
        if (!changedByHistory) {
            if (currentNote != null) {
                History.add(new HistoryItem(currentNote));
                addedToHistory = true;
            }
        }*/

        currentDateLabel.setText(newdate.getFullDateString());
        if ((currentNote != null) && (currentNote.isMarked())) {
            currentDateLabel.setIcon(bookmarkIcon);
            currentDateLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        }
        else {
            currentDateLabel.setIcon(null);
        }
        App.getFrame().setCursor(cur);
    }



    void historyChanged(HistoryItem hi) throws SQLException {
        changedByHistory = true;
        CurrentProject.set(hi.getProject());
        CurrentDate.set(hi.getDate());
        changedByHistory = false;
    }

    /**
     * Toggle button action performed.
     *
     * @param e the e
     */
    void toggleButton_actionPerformed(ActionEvent e) {
        if (expanded) {
            expanded = false;
            toggleButton.setIcon(expIcon);
            controlPanel.remove(toggleToolBar);
            controlPanel.add(toggleToolBar, BorderLayout.EAST);
            splitPane.setDividerLocation((int) controlPanel.getMinimumSize().getWidth());

        }
        else {
            expanded = true;
            toggleButton.setIcon(collIcon);
            controlPanel.remove(toggleToolBar);
            controlPanel.add(toggleToolBar, BorderLayout.SOUTH);
            splitPane.setDividerLocation((int) controlPanel.getPreferredSize().getWidth());
        }
    }






    /**
     * This is the method that switchs the panels when they are selected on the LHS
     *
     *
     *
     * @param pan Can be either Agenda, Classes, Tasks, or Notes
     */
    public void selectPanel(String pan) {
        System.out.println("public void selectPanel(String pan): " +pan);

        switch(pan){
            case "AGENDA":
            {
                agendaPanel.setActive(true);
                break;
            }
            case "NOTES":
            {
                break;
            }
            case "CLASSES":
            {
                break;
            }
        }

        cardLayout1.show(editorsPanel, pan);

        //Kevin Wilkinson - I don't know if this is needed, don't want to completely remove it just yet
        //cardLayout2.show(mainTabsPanel, pan + "TAB");

        calendar.jnCalendar.updateUI();
        this.CurrentPanel=pan;
    }

    /**
     * Gets current panel.
     *
     * @return the current panel
     */
    public String getCurrentPanel() {
        return CurrentPanel;
    }

    /**
     * Task b action performed.
     *
     * @param e the e
     */
    void taskB_actionPerformed(ActionEvent e) {
        parentPanel.tasksB_actionPerformed(null);
    }

    /**
     * used to set the selected class when one is clicked. Only one stored at a time.
     * @param gce Gym Class Entity
     */
    public void setSelectedClass(GymClassEntity gce) {
        this.selectedClass = gce;
    }

    /**
     * Getter for the class currently selected.
     * @return GymClassEntity of selected class
     */
    public GymClassEntity getSelectedClass(){
        return this.selectedClass;
    }

    /**
     * Can be called to refresh all rooms on classes panel.
     */
    public void refreshClassesPanel() throws SQLException {
        classesPanel.refresh();
    }
}