package main.java.memoranda.ui.classes;

import main.java.memoranda.ui.DailyItemsPanel;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;


public class ClassesPanel extends JPanel {


    DailyItemsPanel parentPanelReference = null;

    JToolBar topToolBar = new JToolBar();

    JButton schedNewClassBut = new JButton("Schedule New Class");
    JButton editClassBut = new JButton("Edit Existing Class");
    JButton setAvailabilityBut = new JButton("Set My Availability");
    JButton schedPriClassBut = new JButton("Schedule Private Class");
    JButton enrollClassButt = new JButton("Enroll in Class");
    JButton cancelEnrollmentBut = new JButton("Cancel My Enrollment");
    JButton removeClassBut = new JButton("Remove Class");

    JPanel classesPanel;//contains all the scroll panes

    JScrollPane room1ScrollPane;//these scroll panes contain the roooms
    JScrollPane room2ScrollPane;
    JScrollPane room3ScrollPane;
    JScrollPane room4ScrollPane;

    ClassTable room1;
    ClassTable room2;
    ClassTable room3;
    ClassTable room4;


    public ClassesPanel(DailyItemsPanel _parentPanel) {
        try {
            parentPanelReference = _parentPanel;

            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        this.setLayout(new BorderLayout());
        topToolBar.setFloatable(false);

        initTopToolBar();//initialize our buttons

        initActionListenersTopToolBar();//initialize the action events to our buttons

        initRooms();

        initRoomsActionListeners();

    }

    void initTopToolBar(){

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

        //place all the buttons
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
        this.add(topToolBar, BorderLayout.NORTH);
    }

    void initActionListenersTopToolBar(){

        schedNewClassBut.addActionListener((e)->{
            System.out.println("Debug: schedNewClassBut TODO");
            //TODO
        });
        schedPriClassBut.addActionListener((e)->{
            System.out.println("Debug: schedPriClassBut TODO");
            //TODO
        });
        editClassBut.addActionListener((e)->{
            System.out.println("Debug: editClassBut TODO");
            //TODO
        });
        removeClassBut.addActionListener((e)->{
            System.out.println("Debug: removeClassBut TODO");
            //TODO
        });
        setAvailabilityBut.addActionListener((e)->{
            System.out.println("Debug: setAvailabilityBut TODO");
            //TODO
        });
        enrollClassButt.addActionListener((e)->{
            System.out.println("Debug: enrollClassButt TODO");
            //TODO
        });
        cancelEnrollmentBut.addActionListener((e)->{
            System.out.println("Debug: cancelEnrollmentBut TODO");
            //TODO
        });
    }

    void initRooms(){

        room1 = new ClassTable(this.parentPanelReference,1);
        room2 = new ClassTable(this.parentPanelReference,2);
        room3 = new ClassTable(this.parentPanelReference,3);
        room4 = new ClassTable(this.parentPanelReference,4);

        this.room1ScrollPane = new JScrollPane(room1.classTable);
        this.room2ScrollPane = new JScrollPane(room2.classTable);
        this.room3ScrollPane = new JScrollPane(room3.classTable);
        this.room4ScrollPane = new JScrollPane(room4.classTable);

        this.classesPanel = new JPanel(new FlowLayout());
        this.classesPanel.add(this.room1ScrollPane);
        this.classesPanel.add(this.room2ScrollPane);
        this.classesPanel.add(this.room3ScrollPane);
        this.classesPanel.add(this.room4ScrollPane);

        this.add(classesPanel, BorderLayout.CENTER);


    }

    void initRoomsActionListeners(){
        //TODO
    }


}