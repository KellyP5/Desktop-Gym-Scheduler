package main.java.memoranda.ui.classes;


import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.gym.Response;
import main.java.memoranda.util.Local;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ClassesDeleteClass extends JDialog {

    LocalDate date;
    ClassesPanel topLevelReference;
    private JPanel deleteClass;
    private JButton deleteButton;
    JLabel fillOutForm;
    GymClassEntity selectedClass;


    /**
     * ClassesDeleteClass constructor for  delete class popup.
     */
    public ClassesDeleteClass(ClassesPanel ref, Component rel, LocalDate currentDate, GymClassEntity gce) {

        if (gce == null) {
            throwInputError("You have not selected a class from the Classes Pane");
            return;
        }

        this.topLevelReference = ref;
        this.date = currentDate;
        selectedClass = gce;
        initGuiComponents();
        getContentPane().add(deleteClass);
        setVisible(true);
        setLocationRelativeTo(null);
        actionEventHandler();

    }
    /**
     * initGuiComponents created pop up window for the delete class button.
     */

    public void initGuiComponents() {
        deleteClass = new JPanel();
        //Label
        setTitle("Delete the Class");
        setSize(300, 250);
        //Top Label
        fillOutForm = new JLabel("Are you sure you want to delete this class?");
        fillOutForm.setBounds(25, -10, 275, 50);
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
    public void actionEventHandler() {
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
     * Throws JOptionPane window on error.
     *
     * @param error Message to display to the user
     */
    public void throwInputError(String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }


    /**
     * classDelete deletes class from the schedule.
     *
     * @throws SQLException Throws exception the time is already set
     */
    public void classDelete() throws SQLException {

        String time = selectedClass.getStartTimeAsString();
        double startTime = Local.getDoubleTime(time);
        LocalDate date = this.date;
        //String str = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(date.toString());
        Gym gym = Gym.getInstance();
        int room = selectedClass.getRoomNumber();
        Response delete = gym.deleteClass(date, startTime, room);
        Object[] option = {"OK"};
        int x = JOptionPane.showOptionDialog(null, delete.getMsg(),
                "Deleted", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, option, option[0]);
        if (x == JOptionPane.OK_OPTION) {
            topLevelReference.refresh();
            dispose();
        }
        topLevelReference.refresh();
    }
}