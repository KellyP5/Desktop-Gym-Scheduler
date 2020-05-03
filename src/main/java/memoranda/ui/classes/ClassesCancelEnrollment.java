package main.java.memoranda.ui.classes;

import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.gym.Response;

import javax.swing.*;
import java.time.LocalDate;

/*
Used to attempt to cancel a users enrollment in a class.
 */
public class ClassesCancelEnrollment {
    ClassesPanel classesPanel;
    LocalDate localDate;
    GymClassEntity selectedClass;


    public ClassesCancelEnrollment(ClassesPanel classesPanel, LocalDate localDate, GymClassEntity selectedClass) {
        if (selectedClass == null) {
            showTextStatus("You have not selected a class from the Classes Pane");
            return;
        }
        this.classesPanel = classesPanel;
        this.localDate = localDate;
        this.selectedClass = selectedClass;
        Gym gym = Gym.getInstance();

        Response unenrollResponse = gym.unenrollCustomer(gym.getUser().getEmail(), selectedClass.getId());
        showTextStatus(unenrollResponse.getMsg());

    }

    private void showTextStatus (String message) {
        final JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, message);
    }
}
