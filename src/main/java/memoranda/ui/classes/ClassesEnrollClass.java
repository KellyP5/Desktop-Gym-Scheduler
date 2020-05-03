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

/*
Class instantiated when the user clicks on the enroll in class button.  This attempts to enroll the user in the
selected class.
 */
public class ClassesEnrollClass {
    private static final String tryingGifLocation = "src/main/resources/ui/liftWeight2.gif";
    private static final String failedGifLocation = "src/main/resources/ui/liftWeight2_red.gif";
    private static final String successGifLocation = "src/main/resources/ui/SpinWeight.gif";
    ClassesPanel topLevelReference;
    LocalDate date;
    GymClassEntity selectedClass;
    Gym gym;
    JDialog enrollDialog;
    JLabel infoText;
    JLabel gif;
    Icon tryingIcon;
    Icon failedIcon;
    Icon successIcon;

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
        tryingIcon = new ImageIcon(tryingGifLocation);
        failedIcon = new ImageIcon(failedGifLocation);
        successIcon = new ImageIcon(successGifLocation);

        createGuiElements();
        scheduleUpdatesToGui();
        enrollDialog.setVisible(true);
    }

    /**Check if the gym class is full.
     * @param gce GymClassEntity that is being checked for capacity
     * @return true if the gym class is full
     * @throws SQLException if invalid class information is provided (nonexistant class in database)
     */
    public boolean classIsNotFull(GymClassEntity gce) throws SQLException {
        return App.conn.getDrq().getNumberOfStudentsEnrolledInClass(gce.getId()) < gce.getMaxClassSize();
    }

    /**Check if the user has sufficient training to enroll in the class.
     * @param classSelected class that is being checked against (to get the min belt required)
     * @param loggedInUser  User that is being checked if they have sufficient training to enroll
     * @return
     */
    public boolean userHasSufficientTrainingToEnroll(GymClassEntity classSelected, UserEntity loggedInUser) {
        return loggedInUser.getTrainingBelt().rank.ordinal() >= classSelected.getMinBeltEntityRequired().rank.ordinal();
    }

    /**Checks if the user is already enrolled in a class from the given list of classes and class id.
     * @param usersCurrentEnrolledClasses list of the users classes to check
     * @param classIdAttemptingToEnrollIn id of the class user is attempting to enroll in
     * @return
     */
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
        Icon icon = new ImageIcon(tryingGifLocation);
        gif = new JLabel(icon);
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
                    gif.setIcon(failedIcon);
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
                gif.setIcon(failedIcon);
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
                gif.setIcon(failedIcon);
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
                gif.setIcon(successIcon);
                infoText.setText("User Enrolled!");
            }
            scheduleClose(1500);
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

    private void throwInputError (String error) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, error);
    }
}
