package main.java.memoranda.ui.classes;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;

import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.gym.Response;
import main.java.memoranda.util.Local;

public class ClassesDeleteClass extends JDialog {

    ClassesPanel topLevelReference;
    LocalDate date;
    private JPanel deleteClass;
    private JButton deleteButton;
    private boolean _remove;
    JLabel fillOutForm;

    /**
     * ClassesDeleteClass constructor for our delete class popup.
     *
     *
     */
    public ClassesDeleteClass(ClassesPanel ref, Component rel, LocalDate startDate) {


        this.topLevelReference = ref;


        initGuiComponents();
        actionEventHandler();
        getContentPane().add(deleteClass);
        setVisible(true);
        setLocationRelativeTo(null);


    }

    public void initGuiComponents() {
        deleteClass = new JPanel();
        //Label
        setTitle("Delete the Class");
        setSize(300, 250);
        //Top Label
        fillOutForm = new JLabel("Are you sure you want to delete this class?");
        fillOutForm.setBounds(15, -10, 275, 50);
        fillOutForm.setFont(new Font("Times New Roman", Font.BOLD, 12));

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(90, 100, 120, 20);


        deleteClass.setLayout(null);

        deleteClass.add(fillOutForm);
        deleteClass.add(deleteButton);
        deleteClass.setBackground(new Color(230, 230, 230));

    }

    /**
     * actionEventHandler handles mouse events.
     */
    public void actionEventHandler(){
        /**
         * Manages delete button entry box.
         */

        deleteButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    classDelete();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        classDelete();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * classDelete deletes class from the schedule.
     * @throws SQLException Throws exception the time is already set
     */
    public void classDelete() throws SQLException {



        double startTime = Local.getDoubleTime(toString());
        LocalDate date = this.date;
        System.out.println(date.toString());
        Gym gym = Gym.getInstance();
        GymClassEntity room = null;

        Response availability = gym.deleteClass(date, startTime , room.getRoomNumber());
        /**
         * Popup window that returns appropriate success or fail message.
         */
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, availability.getMsg(),
                "Your Availability", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, option, option[0]);

    }

}
