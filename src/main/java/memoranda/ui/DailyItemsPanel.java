package main.java.memoranda.ui;

import main.java.memoranda.*;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: DailyItemsPanel.java,v 1.22 2005/02/13 03:06:10 rawsushi Exp $*/
public class DailyItemsPanel extends JPanel {


    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();

    CardLayout cardLayout1 = new CardLayout();

    JSplitPane splitPane = new JSplitPane();

    JPanel controlPanel = new JPanel(); /* Contains the calendar */
    JPanel mainPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel editorsPanel = new JPanel();
    JLabel currentDateLabel = new JLabel();



    AgendaPanel agendaPanel = new AgendaPanel(this);

    ImageIcon expIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_right.png"));

    ImageIcon collIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/exp_left.png"));

    ImageIcon bookmarkIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/star8.png"));

    boolean expanded = true;


    CalendarDate currentDate;

    /**
     * The Calendar ignore change.
     */
    boolean calendarIgnoreChange = false;
    /**
     * The Date changed by calendar.
     */
    boolean dateChangedByCalendar = false;
    /**
     * The Changed by history.
     */
    boolean changedByHistory = false;
    /**
     * The Cmain panel.
     */
    JPanel cmainPanel = new JPanel();
    /**
     * The Calendar.
     */
    JNCalendarPanel calendar = new JNCalendarPanel();
    /**
     * The Toggle tool bar.
     */
    JToolBar toggleToolBar = new JToolBar();
    /**
     * The Border layout 5.
     */
    BorderLayout borderLayout5 = new BorderLayout();
    /**
     * The Border 1.
     */
    Border border1;
    /**
     * The Toggle button.
     */
    JButton toggleButton = new JButton();
    /**
     * The Parent panel.
     */
    WorkPanel parentPanel = null;

    /**
     * The Added to history.
     */
    boolean addedToHistory = false;
    /**
     * The Indicators panel.
     */
    JPanel indicatorsPanel = new JPanel();
    /**
     * The Alarm b.
     */
    JButton alarmB = new JButton();
    /**
     * The Flow layout 1.
     */
    FlowLayout flowLayout1 = new FlowLayout();
    /**
     * The Task b.
     */
    JButton taskB = new JButton();
    /**
     * The Main tabs panel.
     */
    JPanel mainTabsPanel = new JPanel();
    /**
     * The Notes control pane.
     */
    NotesControlPanel notesControlPane = new NotesControlPanel();
    /**
     * The Card layout 2.
     */
    CardLayout cardLayout2 = new CardLayout();

    /**
     * The Tasks tabbed pane.
     */
    JTabbedPane tasksTabbedPane = new JTabbedPane();
    /**
     * The Events tabbed pane.
     */
    JTabbedPane eventsTabbedPane = new JTabbedPane();
    /**
     * The Agenda tabbed pane.
     */
    JTabbedPane agendaTabbedPane = new JTabbedPane();
    /**
     * The Border 2.
     */
    Border border2;

    /**
     * The Current panel.
     */
    String CurrentPanel;

    /**
     * The Wait cursor.
     */
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    /**
     * Instantiates a new Daily items panel.
     *
     * @param _parentPanel the parent panel
     */
    public DailyItemsPanel(WorkPanel _parentPanel) {
        try {
            parentPanel = _parentPanel;
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
        controlPanel.setBackground(new Color(255, 0, 230));
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
        alarmB.setMaximumSize(new Dimension(24, 24));
        alarmB.setOpaque(false);
        alarmB.setPreferredSize(new Dimension(24, 24));
        alarmB.setToolTipText(Local.getString("Active events"));
        alarmB.setBorderPainted(false);
        alarmB.setMargin(new Insets(0, 0, 0, 0));
        alarmB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alarmB_actionPerformed(e);
            }
        });
        alarmB.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/alarm.png")));
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



        
        splitPane.add(mainPanel, JSplitPane.RIGHT);
        splitPane.add(controlPanel, JSplitPane.LEFT);
        controlPanel.add(toggleToolBar, BorderLayout.SOUTH);
        toggleToolBar.add(toggleButton, null);

        splitPane.setDividerLocation((int) controlPanel.getPreferredSize().getWidth());
        //splitPane.setResizeWeight(0.0);

        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                currentDateChanged(d);
            }
        });

        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
//            	Util.debug("DailyItemsPanel Project Listener: Project is going to be changed!");				
//            	Util.debug("current project is " + CurrentProject.get().getTitle());

            	currentProjectChanged(p, nl, tl, rl);
            }
            public void projectWasChanged() {
//            	Util.debug("DailyItemsPanel Project Listener: Project has been changed!");            	
//            	Util.debug("current project is " + CurrentProject.get().getTitle());
            	
            	// cannot save note here, changing to new project

                
//                // DEBUG
//                if (currentNote != null) {
//                    Util.debug("currentNote has been set to " + currentNote.getTitle());        	
//                }
//                else {
//                    Util.debug("currentNote has been set to null");
//                }
//                // DEBUG
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
                System.out.println(ev.getTimeString() + " " + ev.getText());
                updateIndicators();
            }

            public void eventsChanged() {
                updateIndicators();
            }
        });



        History.add(new HistoryItem(CurrentDate.get(), CurrentProject.get()));
        cmainPanel.add(mainTabsPanel, BorderLayout.CENTER);
        mainTabsPanel.add(eventsTabbedPane, "EVENTSTAB");
        mainTabsPanel.add(tasksTabbedPane, "TASKSTAB");
        mainTabsPanel.add(notesControlPane, "NOTESTAB");
		mainTabsPanel.add(agendaTabbedPane, "AGENDATAB");
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
		currentDate = CurrentDate.get();

        /*addedToHistory = false;
        if (!changedByHistory) {
            if (currentNote != null) {
                History.add(new HistoryItem(currentNote));
                addedToHistory = true;
            }
        }*/

		currentDateLabel.setText(newdate.getFullDateString());


        updateIndicators(newdate, CurrentProject.getTaskList());
        App.getFrame().setCursor(cur);
    }



    /**
     * Current project changed.
     *
     * @param newprj the newprj
     * @param nl     the nl
     * @param tl     the tl
     * @param rl     the rl
     */
    void currentProjectChanged(Project newprj, NoteList nl, TaskList tl, ResourcesList rl) {
//		Util.debug("currentProjectChanged");

        Cursor cur = App.getFrame().getCursor();
        App.getFrame().setCursor(waitCursor);
        if (!changedByHistory)
            History.add(new HistoryItem(CurrentDate.get(), newprj));

        /*if ((currentNote != null) && !changedByHistory && !addedToHistory)
                    History.add(new HistoryItem(currentNote));*/
        CurrentProject.save();        
        
        /*addedToHistory = false;
        if (!changedByHistory) {
            if (currentNote != null) {
                History.add(new HistoryItem(currentNote));
                addedToHistory = true;
            }
        }*/
        
        updateIndicators(CurrentDate.get(), tl);
        App.getFrame().setCursor(cur);
    }

    /**
     * History changed.
     *
     * @param hi the hi
     */
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
                alarmB.setToolTipText(ev.getTimeString() + " - " + ev.getText());
                indicatorsPanel.add(alarmB, null);
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
     * Select panel.
     *
     * @param pan the pan
     */
    public void selectPanel(String pan) {
        if (calendar.jnCalendar.renderer.getTask() != null) {
            calendar.jnCalendar.renderer.setTask(null);
         //   calendar.jnCalendar.updateUI();
        }
        boolean isAg = pan.equals("AGENDA");
        agendaPanel.setActive(isAg);
        if (isAg)
        	agendaPanel.refresh(CurrentDate.get());
        cardLayout1.show(editorsPanel, pan);
        cardLayout2.show(mainTabsPanel, pan + "TAB");
		calendar.jnCalendar.updateUI();
		CurrentPanel=pan;
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
     * Alarm b action performed.
     *
     * @param e the e
     */
    void alarmB_actionPerformed(ActionEvent e) {
        parentPanel.classesB_actionPerformed(null);
    }
}