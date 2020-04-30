package main.java.memoranda.ui.classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.java.memoranda.util.Local;


/**
 * ClassesSetAvailability class creates pop up window to set up trainer's availability
 */
public class ClassesSetAvailability extends JFrame {

    ClassesPanel topLevelReference;
    LocalDate date;
    JPanel setAvailability;
    JButton setButton;
    JComboBox startTimeCB;
    JLabel lblStartTime;
    JComboBox endTimeCB;
    JLabel lblEndTime;
    JComboBox trainersCB;
    JLabel lblTrainers;
    JLabel currentDate;
    JLabel fillOutForm;

    /** ClassesSetAvailability constructor
     *
     * @param ref This parent reference is passed in so we can call refresh on it once the  UI is updated.
     * @param rel Used to set the position of the add user popup.
     */
    public ClassesSetAvailability(ClassesPanel ref, Component rel, LocalDate currentDate) {
        this.topLevelReference = ref;
        date = currentDate;
        guiSetAvailability();
        actionEventHandler();
        getContentPane().add(setAvailability);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    /**
     * guiSetAvailability contains gui components for set availability button
     */
    public void guiSetAvailability(){
        setAvailability = new JPanel();
        //Label
        setTitle("Set My Availability");
        setSize(300, 250);
        //Top Label
        fillOutForm = new JLabel("Please select your available time for chosen date:");
        fillOutForm.setBounds(15, -10, 275, 50);
        fillOutForm.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        //Current Date Label
        currentDate = new JLabel(date.toString());
        currentDate.setBounds(100, 10, 200, 50);
        currentDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        //Trainers CB and Label
        trainersCB = new JComboBox(Local.getTrainerNames());
        lblTrainers = new JLabel("Your email:");
        trainersCB.setBounds(90, 50, 200, 20);
        lblTrainers.setBounds(5, 50, 100, 25);
        //start time CB and Label
        startTimeCB = new JComboBox(Local.getTimes());
        lblStartTime = new JLabel("Available From:");
        startTimeCB.setBounds(100, 75, 70, 20);
        lblStartTime.setBounds(5, 75, 125, 25);
        //end time CB and Label
        endTimeCB = new JComboBox(Local.getTimes());
        lblEndTime = new JLabel("Available Till:");
        endTimeCB.setBounds(100, 100, 70, 20);
        lblEndTime.setBounds(5, 100, 125, 25);

        setButton = new JButton("Confirm");
        setButton.setBounds(100, 190, 100, 20);


        setAvailability.setLayout(null);

        setAvailability.add(setButton);
        setAvailability.add(lblTrainers);
        setAvailability.add(lblEndTime);
        setAvailability.add(lblStartTime);
        setAvailability.add(currentDate);
        setAvailability.add(startTimeCB);
        setAvailability.add(endTimeCB);
        setAvailability.add(fillOutForm);
        setAvailability.add(trainersCB);
        setAvailability.setBackground(new Color(230, 230, 230));
    }

    /**
     * actionEventHandler handles mouse events
     */
    public void actionEventHandler(){
        /**
         * Manages create button entry box
         */

        setButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    availabilitySetup();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        availabilitySetup();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Throws JOptionPane window on error.
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }

    /**
     * availabilitySetup stores trainer's entered availability
     * @throws SQLException Throws exception the time is already set
     */
    public void availabilitySetup () throws SQLException {



        String userEmail = extractTrainerEmail();
        double start = Local.getDoubleTime(startTimeCB.getSelectedItem().toString());
        double end = Local.getDoubleTime(endTimeCB.getSelectedItem().toString());
        LocalDate date = this.date;

        System.out.println(date.toString());


    }

    /**
     * Popup window that tells the user the account was created successfully
     */
    public void showCreatedSuccessfullyPopup() {
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, "Class was created successfully!",
                "Class Creation", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
    }


    /**
     * Extracts trainer email from combo box.
     * @return String of email
     */
    public String extractTrainerEmail() {
        String trainer = trainersCB.getSelectedItem().toString();
        String email = "";
        int j;
        for (int i = 0; i < trainer.length(); i++) {
            if (trainer.charAt(i) == ' ') {
                email = trainer.substring(0, i);
                i = trainer.length();
            }
        }
        return email;
    }

}

