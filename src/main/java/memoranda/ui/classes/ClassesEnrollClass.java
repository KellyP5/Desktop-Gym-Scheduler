package main.java.memoranda.ui.classes;

import main.java.memoranda.database.entities.GymClassEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
        printSelectedClass();
        initActionEvents();
    }

    public void printSelectedClass(){
        System.out.println("CLASSES ENROLL CLASS" + selectedClass.getTrainerEmail() + " Belt: " + selectedClass.getMinBeltEntityRequired().toString());
    }

    public void initActionEvents() {
        Icon icon = new ImageIcon(enrollDialogGifLocation);

        JLabel label = new JLabel(icon);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topLevelReference);
        enrollDialog = new JDialog(topFrame, true);
        enrollDialog.setUndecorated(true);
        enrollDialog.getContentPane().add(label);
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

    /**
     * Throws JOptionPane window on error.
     * @param error Message to display to the user
     */
    public void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }
}
