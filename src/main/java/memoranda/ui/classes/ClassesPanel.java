package main.java.memoranda.ui.classes;

import main.java.memoranda.Start;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.ui.DailyItemsPanel;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.usermanagment.UserManagementRemoveUser;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClassesPanel extends JPanel {

    private DailyItemsPanel parentPanelReference = null;

    private JToolBar topToolBar = new JToolBar();

    private LocalDate date;

    private Gym gym = Gym.getInstance();

    private JButton schedNewClassBut;//all the buttons for the top bar
    private JButton editClassBut;
    private JButton setAvailabilityBut;
    private JButton schedPriClassBut;
    private JButton enrollClassButt;
    private JButton cancelEnrollmentBut;
    private JButton removeClassBut;

    private JPanel colorKey;

    private JPanel classesPanelTop;//contains scroll panes 1,2
    private JPanel classesPanelBot;//contains scroll panes 3,4

    private JScrollPane room1ScrollPane;//these scroll panes contain the roooms
    private JScrollPane room2ScrollPane;

    private JScrollPane room3ScrollPane;
    private JScrollPane room4ScrollPane;

    private ClassTable room1;
    private ClassTable room2;
    private ClassTable room3;
    private ClassTable room4;

    private UserEntity loggedInUser;

    public ClassesPanel(DailyItemsPanel _parentPanel) {
        try {
            loggedInUser = Gym.getInstance().getUser();
            date = LocalDate.of(_parentPanel.currentDate.getYear(),
                    _parentPanel.currentDate.getMonth()+1,
                    _parentPanel.currentDate.getDay());
            parentPanelReference = _parentPanel;

            init();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Main init method for the ClassesPanel
     */
    private void init() throws SQLException {
        date = LocalDate.of(parentPanelReference.currentDate.getYear(),
                parentPanelReference.currentDate.getMonth()+1,
                parentPanelReference.currentDate.getDay());
        this.setLayout(new BorderLayout());
        topToolBar.setFloatable(false);

        initTopToolBar();//initialize our buttons

        initActionListenersTopToolBar();//initialize the action events to our buttons

        initRooms();//each room handles its own action listener

    }

    /**
     * Refreshes each of the rooms on the Classes Panel.
     * Used when classes are edited or added.
     */
    public void refresh() throws SQLException {
        room1.refresh();
        room2.refresh();
        room3.refresh();
        room4.refresh();
    }

    /**
     * All code associated with initializing the toolbar.
     */
    private void initTopToolBar(){

        schedNewClassBut = new JButton("Schedule New Class");
        editClassBut = new JButton("Edit Existing Class");
        setAvailabilityBut = new JButton("Set My Availability");
        schedPriClassBut = new JButton("Schedule Private Class");
        enrollClassButt = new JButton("Enroll in Class");
        cancelEnrollmentBut = new JButton("Cancel My Enrollment");
        removeClassBut = new JButton("Remove Class");

        Color color = Color.decode("#16034f");
        schedNewClassBut.setBackground(color);
        schedNewClassBut.setForeground(Color.WHITE);
        schedNewClassBut.setEnabled(true);
        schedNewClassBut.setMaximumSize(new Dimension(130, 24));
        schedNewClassBut.setMinimumSize(new Dimension(130, 24));
        schedNewClassBut.setToolTipText(Local.getString("Schedule Class"));
        schedNewClassBut.setRequestFocusEnabled(false);
        schedNewClassBut.setPreferredSize(new Dimension(130, 24));
        schedNewClassBut.setFocusable(false);
        schedNewClassBut.setBorderPainted(false);
        schedNewClassBut.setFont(new Font("Arial", Font.PLAIN, 10));

        schedPriClassBut.setBackground(color);
        schedPriClassBut.setForeground(Color.WHITE);
        schedPriClassBut.setEnabled(true);
        schedPriClassBut.setMaximumSize(new Dimension(130, 24));
        schedPriClassBut.setMinimumSize(new Dimension(130, 24));
        schedPriClassBut.setToolTipText(Local.getString("Schedule Private Class"));
        schedPriClassBut.setRequestFocusEnabled(false);
        schedPriClassBut.setPreferredSize(new Dimension(130, 24));
        schedPriClassBut.setFocusable(false);
        schedPriClassBut.setBorderPainted(false);
        schedPriClassBut.setFont(new Font("Arial", Font.PLAIN, 10));

        editClassBut.setBackground(Color.GRAY);
        editClassBut.setForeground(Color.WHITE);
        editClassBut.setBorderPainted(false);
        editClassBut.setFocusable(false);
        editClassBut.setPreferredSize(new Dimension(130, 24));
        editClassBut.setRequestFocusEnabled(false);
        editClassBut.setToolTipText(Local.getString("Edit Existing Class"));
        editClassBut.setMinimumSize(new Dimension(130, 24));
        editClassBut.setMaximumSize(new Dimension(130, 24));
        editClassBut.setEnabled(true);
        editClassBut.setFont(new Font("Arial", Font.PLAIN, 10));

        removeClassBut.setBackground(Color.GRAY);
        removeClassBut.setForeground(Color.WHITE);
        removeClassBut.setBorderPainted(false);
        removeClassBut.setFocusable(false);
        removeClassBut.setPreferredSize(new Dimension(130, 24));
        removeClassBut.setRequestFocusEnabled(false);
        removeClassBut.setToolTipText(Local.getString("Remove event"));
        removeClassBut.setMinimumSize(new Dimension(130, 24));
        removeClassBut.setMaximumSize(new Dimension(130, 24));
        removeClassBut.setFont(new Font("Arial", Font.PLAIN, 10));

        setAvailabilityBut.setBackground(Color.GRAY);
        setAvailabilityBut.setForeground(Color.WHITE);
        setAvailabilityBut.setEnabled(true);
        setAvailabilityBut.setMaximumSize(new Dimension(130, 24));
        setAvailabilityBut.setMinimumSize(new Dimension(130, 24));
        setAvailabilityBut.setToolTipText(Local.getString("Set Availability"));
        setAvailabilityBut.setRequestFocusEnabled(false);
        setAvailabilityBut.setPreferredSize(new Dimension(130, 24));
        setAvailabilityBut.setFocusable(false);
        setAvailabilityBut.setBorderPainted(false);
        setAvailabilityBut.setFont(new Font("Arial", Font.PLAIN, 10));

        Color color1 = Color.decode("#5a2980");
        enrollClassButt.setBackground(color1);
        enrollClassButt.setForeground(Color.WHITE);
        enrollClassButt.setBorderPainted(false);
        enrollClassButt.setFocusable(false);
        enrollClassButt.setPreferredSize(new Dimension(130, 24));
        enrollClassButt.setRequestFocusEnabled(false);
        enrollClassButt.setToolTipText(Local.getString("Enroll in Class"));
        enrollClassButt.setMinimumSize(new Dimension(130, 24));
        enrollClassButt.setMaximumSize(new Dimension(130, 24));
        enrollClassButt.setEnabled(true);
        enrollClassButt.setFont(new Font("Arial", Font.PLAIN, 10));

        cancelEnrollmentBut.setBackground(color1);
        cancelEnrollmentBut.setForeground(Color.WHITE);
        cancelEnrollmentBut.setBorderPainted(false);
        cancelEnrollmentBut.setFocusable(false);
        cancelEnrollmentBut.setPreferredSize(new Dimension(130, 24));
        cancelEnrollmentBut.setRequestFocusEnabled(false);
        cancelEnrollmentBut.setToolTipText(Local.getString("Edit My Enrolled Class"));
        cancelEnrollmentBut.setMinimumSize(new Dimension(130, 24));
        cancelEnrollmentBut.setMaximumSize(new Dimension(130, 24));
        cancelEnrollmentBut.setEnabled(true);
        cancelEnrollmentBut.setFont( new Font("Arial", Font.PLAIN, 10));

        // Add the key for the color of the classes
        // Green = Open, Red = Full
        this.colorKey = new JPanel();
        JLabel open = new JLabel("Open");

        JPanel whiteOpen = new JPanel();
        whiteOpen.setPreferredSize(new Dimension(10,10));
        whiteOpen.setBackground(Color.WHITE);

        JLabel full = new JLabel("Full");

        JPanel redClosed = new JPanel();
        redClosed.setPreferredSize(new Dimension(10,10));
        redClosed.setBackground(Color.RED);

        JLabel key = new JLabel("Key: ");
        colorKey.add(key);
        colorKey.add(open);
        colorKey.add(whiteOpen);
        colorKey.add(full);
        colorKey.add(redClosed);

        //places buttons based on the logged in users role
        loggedInUser = Start.getGym().getUser();
        if (loggedInUser.isTrainer()) {
            topToolBar.add(schedPriClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(removeClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(setAvailabilityBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(enrollClassButt, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(cancelEnrollmentBut, null);
        } else if (loggedInUser.isCustomer()) {
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(enrollClassButt, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(cancelEnrollmentBut, null);
        } else {
            topToolBar.add(schedNewClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(schedPriClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(editClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(removeClassBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(setAvailabilityBut, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(enrollClassButt, null);
            topToolBar.addSeparator(new Dimension(2, 24));
            topToolBar.add(cancelEnrollmentBut, null);
        }
        this.add(topToolBar, BorderLayout.NORTH);

        this.setVisible(true);
    }

    /**
     * TODO This is where the event handlers will call our popups for modifying the classes.
     *
     * TODO NOT IMPLEMENTED!
     */
    private void initActionListenersTopToolBar(){

        schedNewClassBut.addActionListener((e)->{
            new ClassesSchedNewClass(this, schedNewClassBut, LocalDate.of(parentPanelReference.currentDate.getYear(),
                    parentPanelReference.currentDate.getMonth()+1,
                    parentPanelReference.currentDate.getDay()));
        });
        schedPriClassBut.addActionListener((e)->{
            new ClassesSchedPrivClass(this, schedNewClassBut, LocalDate.of(parentPanelReference.currentDate.getYear(),
                    parentPanelReference.currentDate.getMonth()+1,
                    parentPanelReference.currentDate.getDay()));
        });
        editClassBut.addActionListener((e)->{
            new ClassesEditExistingClass(this, schedNewClassBut, LocalDate.of(parentPanelReference.currentDate.getYear(),
                parentPanelReference.currentDate.getMonth()+1,
                parentPanelReference.currentDate.getDay()), parentPanelReference.getSelectedClass());
        });
        removeClassBut.addActionListener((e)->{
            new ClassesDeleteClass(this, removeClassBut, LocalDate.of(parentPanelReference.currentDate.getYear(),
                                parentPanelReference.currentDate.getMonth()+1,
                                parentPanelReference.currentDate.getDay()), parentPanelReference.getSelectedClass());

        });
        setAvailabilityBut.addActionListener((e)->{
            new ClassesSetAvailability(this, setAvailabilityBut, LocalDate.of(parentPanelReference.currentDate.getYear(),
                    parentPanelReference.currentDate.getMonth()+1,
                    parentPanelReference.currentDate.getDay()));
        });
        enrollClassButt.addActionListener((e)->{
            new ClassesEnrollClass(this,
                        LocalDate.of(parentPanelReference.currentDate.getYear(),
                        parentPanelReference.currentDate.getMonth()+1,
                        parentPanelReference.currentDate.getDay()),
                    parentPanelReference.getSelectedClass());
        });
        cancelEnrollmentBut.addActionListener((e)->{
            new ClassesCancelEnrollment(this,
                    LocalDate.of(parentPanelReference.currentDate.getYear(),
                            parentPanelReference.currentDate.getMonth()+1,
                            parentPanelReference.currentDate.getDay()),
                    parentPanelReference.getSelectedClass());
        });
    }

    /**
     * All code associated with initializing the class tables.
     */
    private void initRooms() throws SQLException {

        room1 = new ClassTable(this.parentPanelReference,1);
        room2 = new ClassTable(this.parentPanelReference,2);
        room3 = new ClassTable(this.parentPanelReference,3);
        room4 = new ClassTable(this.parentPanelReference,4);

        this.room1ScrollPane = new JScrollPane(room1.classTable);
        this.room2ScrollPane = new JScrollPane(room2.classTable);
        this.room3ScrollPane = new JScrollPane(room3.classTable);
        this.room4ScrollPane = new JScrollPane(room4.classTable);

        this.classesPanelTop = new JPanel(new FlowLayout());
        this.classesPanelBot= new JPanel(new FlowLayout());

        this.classesPanelTop.add(this.room1ScrollPane);
        // Add titles to rooms
        this.room1ScrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(),
                "Room 1", TitledBorder.CENTER, TitledBorder.TOP));
        this.classesPanelTop.add(this.room2ScrollPane);
        this.room2ScrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(),
                "Room 2", TitledBorder.CENTER, TitledBorder.TOP));
        this.classesPanelBot.add(this.room3ScrollPane);
        this.room3ScrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(),
                "Room 3", TitledBorder.CENTER, TitledBorder.TOP));
        this.classesPanelBot.add(this.room4ScrollPane);
        this.room4ScrollPane.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(),
                "Room 4", TitledBorder.CENTER, TitledBorder.TOP));
        
        this.add(classesPanelTop, BorderLayout.CENTER);
        this.add(classesPanelBot, BorderLayout.SOUTH);
    }
}