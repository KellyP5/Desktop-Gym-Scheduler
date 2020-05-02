package main.java.memoranda.ui.classes;

import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.gym.Gym;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClassesEnrollClass {
    private static final String enrollDialogGifLocation = "src/main/resources/ui/liftWeight.gif";
    ClassesPanel topLevelReference;
    LocalDate date;
    GymClassEntity selectedClass;
    JDialog enrollDialog;

    public ClassesEnrollClass(ClassesPanel topLevelReference, LocalDate date, GymClassEntity gce) {
        if (gce == null) {
            throwInputError("You have not selected a class from the Classes Pane");
            return;
        }

        this.topLevelReference = topLevelReference;
        this.date = date;
        this.selectedClass = gce;
        initActionEvents();
    }


    public void initActionEvents() {
        Icon icon = new ImageIcon(enrollDialogGifLocation);

        JLabel gif = new JLabel(icon);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topLevelReference);
        enrollDialog = new JDialog(topFrame, true);
        enrollDialog.setUndecorated(true);
        enrollDialog.getContentPane().add(gif);


        JLabel infoText = new JLabel(); //TODO


        enrollDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        enrollDialog.pack();
        enrollDialog.setLocationRelativeTo(topFrame);
        enrollDialog.setSize(400, 400);


        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent event){
                enrollDialog.dispose();
            }
        };

        Timer t = new Timer(5000, listener);
        t.setRepeats(false);
        t.start();

        enrollDialog.setVisible(true);
    }

    public boolean classIsNotFull() {
        int roomNumber = selectedClass.getRoomNumber();
        LocalDateTime localdt = selectedClass.getStartDateTime();
        String startTime = selectedClass.getStartTimeAsString();

        //TODO
        return false;
    }


    /**
     * Throws JOptionPane window on error.
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }
}
