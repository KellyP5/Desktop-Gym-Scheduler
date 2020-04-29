package main.java.memoranda.ui.classes;

import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.ui.App;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

        /**
        * ClassesSetAvailability class creates pop up window to set up availability.
        */
public class ClassesSetAvailability extends JFrame {

            ClassesPanel topLevelReference;
            LocalDate date;
            JPanel setAvailability;
            JButton setButton;
            JComboBox startTime;
            JLabel lblStartTime;
            JComboBox endTime;
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
                guiPopUp();
                initActionEvents();
                getContentPane().add(setAvailability);
                setVisible(true);
                setLocationRelativeTo(null);
            }

            /**
             * guiPopUp class contains GUI for set availability button pop up
             */
            public void guiPopUp(){
                setAvailability = new JPanel();
                //Label
                setTitle("Set My Availability");
                setSize(300, 250);
                //Top Label
                fillOutForm = new JLabel("Please choose you available hours for the following date:");
                fillOutForm.setBounds(15, -10, 275, 50);
                fillOutForm.setFont(new Font("Times New Roman", Font.PLAIN, 12));

                //Current Date Label
                currentDate = new JLabel(date.toString());
                currentDate.setBounds(100, 10, 200, 50);
                currentDate.setFont(new Font("Times New Roman", Font.BOLD, 12));

                //Trainers CB and Label
                trainersCB = new JComboBox(Local.getTrainerNames());
                lblTrainers = new JLabel("Select Your Email:");
                trainersCB.setBounds(100, 50, 200, 20);
                lblTrainers.setBounds(5, 50, 100, 25);

                //Start Times CB and Label
                startTime = new JComboBox(Local.getTimes());
                lblStartTime = new JLabel("Available From:");
                startTime.setBounds(150, 75, 70, 20);
                lblStartTime.setBounds(25, 75, 125, 25);
                //End Time CB and Label
                endTime = new JComboBox(Local.getBeltNames());
                lblStartTime = new JLabel("Select Belt Level:");
                endTime.setBounds(150, 100, 70, 25);
                lblEndTime.setBounds(25, 100, 125, 25);

                setAvailability.setLayout(null);

                setAvailability.add(setButton);
                setAvailability.add(lblTrainers);
                setAvailability.add(lblStartTime);
                setAvailability.add(startTime);
                setAvailability.add(currentDate);
                setAvailability.add(endTime);
                setAvailability.add(lblEndTime);
                setAvailability.add(fillOutForm);
                setAvailability.add(trainersCB);
                setAvailability.setBackground(new Color(230, 230, 230));
            }


            /**
             * actionEventsHandler event handler
             */
            public void actionEventsHandler(){


                setButton.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            availabilityButton();

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
                                availabilityButton();
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
             * Call to create account. Tests fields on Account Creation window for data.
             * @throws SQLException Throws exception is account does not exist.
             */
            public void availabilityButton () throws SQLException {

            }

        }
