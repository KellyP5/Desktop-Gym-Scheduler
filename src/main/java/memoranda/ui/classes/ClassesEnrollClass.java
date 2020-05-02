package main.java.memoranda.ui.classes;

import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.gym.Response;
import main.java.memoranda.ui.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClassesEnrollClass {
    private static final String enrollDialogGifLocation = "src/main/resources/ui/liftWeight.gif";
    ClassesPanel topLevelReference;
    LocalDate date;
    GymClassEntity selectedClass;
    Gym gym;
    JDialog enrollDialog;
    JLabel infoText;
    boolean shouldUpdateText;

    public ClassesEnrollClass(ClassesPanel topLevelReference, LocalDate date, GymClassEntity selectedClass) {
        if (selectedClass == null) {
            throwInputError("You have not selected a class from the Classes Pane");
            return;
        }

        this.topLevelReference = topLevelReference;
        this.date = date;
        this.selectedClass = selectedClass;
        this.shouldUpdateText = true;
        this.gym = Gym.getInstance();
        createGuiElements();

        scheduleUpdatesToGui();
        enrollDialog.setVisible(true);
    }

    public boolean classIsNotFull(GymClassEntity gce) throws SQLException {
        return App.conn.getDrq().getNumberOfStudentsEnrolledInClass(gce.getId()) < gce.getMaxClassSize();
    }

    public boolean userHasSufficientTrainingToEnroll(GymClassEntity classSelected, UserEntity loggedInUser) {
        return loggedInUser.getTrainingBelt().rank.ordinal() >= classSelected.getMinBeltEntityRequired().rank.ordinal();
    }

    public boolean userIsAlreadyEnrolledInClass(ArrayList<GymClassEntity> usersCurrentEnrolledClasses,
                                                int classIdAttemptingToEnrollIn) {
        for (GymClassEntity gymClassEntity : usersCurrentEnrolledClasses) {
            if (gymClassEntity.getId() == classIdAttemptingToEnrollIn) {
                return true;
            }
        }
        return false;
    }


    private void scheduleUpdatesToGui() {
        scheduleDelayedTextUpdate("Checking you aren't already enrolled in this class...", 0);
        scheduleDelayedStudentIsntAlreadyEnrolledCheck(1000);
        scheduleDelayedTextUpdate("Checking your belt rank is sufficient...", 2000);
        scheduleDelayedStudentBeltRankIsSufficientCheck(3000);
        scheduleDelayedTextUpdate("Checking class availability...", 4000);
        scheduleDelayedClassSizeCheck(5000);
        scheduleDelayedTextUpdate("Enrolling in class...", 6000);
        scheduleDelayedEnrollUser(7000);
    }


    private void createGuiElements() {
        //setup gif
        Icon icon = new ImageIcon(enrollDialogGifLocation);
        JLabel gif = new JLabel(icon);
        gif.setAlignmentX(Component.CENTER_ALIGNMENT);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topLevelReference);

        //setup enroll dialog (which contains gif and text)
        enrollDialog = new JDialog(topFrame, true);
        enrollDialog.setLayout(new BoxLayout(enrollDialog.getContentPane(), BoxLayout.Y_AXIS));
        enrollDialog.setUndecorated(true);
        enrollDialog.add(gif);

        //add some space
        enrollDialog.add(Box.createRigidArea(new Dimension(0, 30)));

        //add text (to start its empty so this is a placeholder)
        infoText = new JLabel("");
        infoText.setAlignmentX(Component.CENTER_ALIGNMENT);
        enrollDialog.add(infoText);

        //finish configuration of dialog
        enrollDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        enrollDialog.pack();
        enrollDialog.setLocationRelativeTo(topFrame);
        enrollDialog.setSize(350, 200);
    }

    private void scheduleDelayedClassSizeCheck(int delay) {
        ActionListener listener = event -> {
            if (!shouldUpdateText) {
                return;
            }
            try {
                if (!classIsNotFull(selectedClass)) {
                    shouldUpdateText = false;
                    infoText.setText("Class is full!");
                    scheduleClose(2000);
                }
            } catch (SQLException e) {
                shouldUpdateText = false;
                scheduleClose(2000);
            }
        };
        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();

    }

    private void scheduleDelayedStudentBeltRankIsSufficientCheck(int delay) {
        ActionListener listener = event -> {
            if (!shouldUpdateText) {
                return;
            }

            if (!userHasSufficientTrainingToEnroll(selectedClass, gym.getUser())) {
                shouldUpdateText = false;
                infoText.setText("Belt rank is insufficient to enroll in that class!");
                scheduleClose(2000);
            }
        };
        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();
    }



    private void scheduleDelayedStudentIsntAlreadyEnrolledCheck(int delay) {
        ActionListener listener = event -> {
            if (!shouldUpdateText) {
                return;
            }
            Response response = gym.getClassesUserEnrolledInByEmail(gym.getUser().getEmail());


            if (response.isFailure()
                    || userIsAlreadyEnrolledInClass((ArrayList<GymClassEntity>)response.getValue(),
                    selectedClass.getId())) {
                shouldUpdateText = false;
                infoText.setText("You're already enrolled!");
                scheduleClose(2000);
            }

        };
        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();

    }

    private void scheduleDelayedEnrollUser(int delay) {
        ActionListener listener = event -> {
            if (!shouldUpdateText) {
                return;
            }
            Response response = gym.enrollUser(selectedClass.getId(), gym.getUser().getEmail());
            if (response.isSuccess()) {
                infoText.setText("User Enrolled!");
            }
            scheduleClose(1000);
        };
        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();

    }

    private void scheduleDelayedTextUpdate(String text, int delay) {
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                if (shouldUpdateText) {
                    infoText.setText(text);
                }
            }
        };

        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();
    }

    private void scheduleClose(int delay) {
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                enrollDialog.dispose();
            }
        };
        Timer t = new Timer(delay, listener);
        t.setRepeats(false);
        t.start();
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
