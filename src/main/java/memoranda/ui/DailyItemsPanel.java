package main.java.memoranda.ui;

import main.java.memoranda.*;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.classes.ClassesPanel;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    
    TaskPanel tasksPanel = null;
    ClassesPanel classesPanel = null;
    AgendaPanel agendaPanel = null;

    ImageIcon expIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_right.png"));
    ImageIcon collIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_left.png"));
    ImageIcon bookmarkIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/star8.png"));

    boolean expanded = true;

    public CalendarDate currentDate;

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

    JButton taskB = new JButton();

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
            tasksPanel = new TaskPanel(this);
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
        taskB.setMargin(new Insets(0, 0, 0, 0));
        taskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskB_actionPerformed(e);
            }
        });
        taskB.setPreferredSize(new Dimension(24, 24));
        taskB.setToolTipText(Local.getString("Active to-do tasks"));
        taskB.setBorderPainted(false);
        taskB.setMaximumSize(new Dimension(24, 24));
        taskB.setOpaque(false);
        taskB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/task.png")));

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
        editorsPanel.add(tasksPanel, "TASKS");
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
                CurrentDate.set(calendar.get());
                dateChangedByCalendar = false;
            }
        });



        History.addHistoryListener(new HistoryListener() {
            public void historyWasRolledTo(HistoryItem hi) {
                historyChanged(hi);
            }
        });

        EventsScheduler.addListener(new EventNotificationListener() {
            public void eventIsOccured(main.java.memoranda.Event ev) {
                /*DEBUG*/
                System.out.println("Debug 00: "+"ev.getTimeString() "+ " " + ev.getText());
                updateIndicators();
            }

            public void eventsChanged() {
                updateIndicators();
            }
        });


        currentNote = CurrentProject.getNoteList().getNoteForDate(CurrentDate.get());
        CurrentNote.set(currentNote,true);
        editorPanel.setDocument(currentNote);
        History.add(new HistoryItem(CurrentDate.get(), CurrentProject.get()));
        cmainPanel.add(mainTabsPanel, BorderLayout.CENTER);
        updateIndicators(CurrentDate.get(), CurrentProject.getTaskList());
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

        updateIndicators(newdate, CurrentProject.getTaskList());
        App.getFrame().setCursor(cur);
    }



    void historyChanged(HistoryItem hi) {
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
     * Update indicators.
     *
     * @param date the date
     * @param tl   the tl
     */
    public void updateIndicators(CalendarDate date, TaskList tl) {
        indicatorsPanel.removeAll();
        if (date.equals(CalendarDate.today())) {
            if (tl.getActiveSubTasks(null,date).size() > 0)
                indicatorsPanel.add(taskB, null);
            if (EventsScheduler.isEventScheduled()) {
                /*String evlist = "";
                for (Iterator it = EventsScheduler.getScheduledEvents().iterator(); it.hasNext();) {
                    net.sf.memoranda.Event ev = (net.sf.memoranda.Event)it.next();
                    evlist += ev.getTimeString()+" - "+ev.getText()+"\n";
                } */
                main.java.memoranda.Event ev = EventsScheduler.getFirstScheduledEvent();

            }
        }
        indicatorsPanel.updateUI();
    }

    /**
     * Update indicators.
     */
    public void updateIndicators() {
        updateIndicators(CurrentDate.get(), CurrentProject.getTaskList());
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
            case "TASKS":
            {

                break;
            }
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


}