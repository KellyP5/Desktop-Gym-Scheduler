package main.java.memoranda.ui.classes;

import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.ui.App;

import java.sql.SQLException;

public class ClassHelper {
    public boolean classIsNotFull(GymClassEntity gce) throws SQLException {
        return App.conn.getDrq().getNumberOfStudentsEnrolledInClass(gce.getId()) < gce.getMaxClassSize();
    }

    public boolean userHasSufficientTrainingToEnroll(GymClassEntity classSelected, UserEntity loggedInUser) {
        return loggedInUser.getTrainingBelt().rank.ordinal() >= classSelected.getMinBeltEntityRequired().rank.ordinal();
    }

}
