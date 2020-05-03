package main.java.memoranda.gym;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.entities.BeltEntity;
import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.database.entities.RoleEntity;
import main.java.memoranda.database.entities.TrainerAvailabilityEntity;
import main.java.memoranda.database.entities.UserEntity;
import main.java.memoranda.database.util.SqlConstants;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.LoginBox;
import org.sqlite.SQLiteException;

/**
 * ---CreateMethods
 * createAdmin
 * createTrainer
 * createCustomer
 * createGroupClass
 * createPrivateClass
 * createTrainerAvailability
 * ---Read
 * readGetClass
 * readGetUser
 * ---UpdateMethods
 * ---DeleteMethods
 * deleteUser
 * deleteClass
 */
public class Gym {

    //This variable will be the currently logged in user.
    private static UserEntity user;
    private static SqlConnection conn;
    private LoginBox login;
    private static Gym gym;

    public enum UserRole {
        admin,
        trainer,
        customer
    }

    private Gym() {

        this.user = null;
        this.conn = null;

        try {
            this.conn = SqlConnection.getInstance();
        } catch (SQLException ecp) {
            ecp.printStackTrace();
        }
    }

    public static Gym getInstance() {
        if (gym == null) {
            gym = new Gym();
        }
        return gym;
    }

    /**
     * Returns Role Entity.
     *
     * @return the Role Entity
     */
    public RoleEntity getUserRole() {
        return user.getRole();
    }

    /**
     * Gets the currently logged in user
     * @return The user
     */
    public UserEntity getUser() {
        return this.user;
    }

    /**
     * Sets the logged in user
     * @param user The currently logged in user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    //TODO needs refactoring
    public static ArrayList<GymClassEntity> getEnrolledClassesByEmailAndDate(String email,
                                                                             LocalDate selectedCalendarDate) {
        try {
            return App.conn.getDrq().getEnrolledClassByEmailAndDate(email, selectedCalendarDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //DB Encapsulated Create methods /////////////////////////////////////////////////////////

    /*


        String userEmail = extractTrainerEmail();
        double start = Local.getDoubleTime(startTimeCB.getSelectedItem().toString());
        double end = Local.getDoubleTime(endTimeCB.getSelectedItem().toString());
        LocalDate date = this.date;

     */

    /**
     * Creates trainer availability.
     *
     * @param email     The email of the trainer.
     * @param startTime The start time.
     * @param endTime   The end time.
     * @param date      the date.
     * @return
     */
    public Response createTrainerAvailability(String email, double startTime, double endTime,
                                              LocalDate date) {

/*        //04/29/2020
        LocalDate date = LocalDate.parse(pdate.toString(), SqlConstants.DBDATEFORMAT);*/

        TrainerAvailabilityEntity newTAE =
            new TrainerAvailabilityEntity(email, date, startTime, endTime);

        //check if the user is a trainer
        //PRIMARY KEY(TrainerEmail, StartDate, StartTime, EndTime)

        try {

            UserEntity ue = conn.getDrq().getUserByEmail(email);

            if (ue == null) {
                return Response.failure("Error: User does not exists.");
            }
            if (!ue.isTrainer()) {
                return Response.failure("Error: User is not a trainer.");
            }

            ArrayList<TrainerAvailabilityEntity> curAvail =
                conn.getDrq().getTrainerDateTimeAvailabilityByEmail(email);

            //returns values in the format of 1998-04-28T05:00

            //need to see what is the current availabilty
            for (int i = 0; i < curAvail.size(); i++) {
                TrainerAvailabilityEntity te = curAvail.get(i);
                if (te.equals(newTAE)) {
                    return Response.failure("Error: This availability already exists");
                }
            }


            String startDate = date.format(SqlConstants.DBDATEFORMAT);


            conn.getDcq().insertTrainerAvailability(email, startDate, startTime, endTime);
            curAvail = conn.getDrq().getTrainerDateTimeAvailabilityByEmail(email);

            for (int i = 0; i < curAvail.size(); i++) {
                TrainerAvailabilityEntity te = curAvail.get(i);
                if (te.equals(newTAE)) {
                    return Response.success("Success: Trainers availability has been added.");
                }
            }

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.failure("Error: You should not see this.");
    }


    /**
     * Creates an admin user if the user does not already exists in the system.
     *
     * @param email The email used.
     * @param fname The first name.
     * @param lname The last name.
     * @param pwd   The password.
     * @return A Response which contains a bool that dictates success/failure and the msg on why
     */
    public Response createAdmin(String email,
                                String fname,
                                String lname,
                                String pwd) {

        try {
            //test if the user already exists.
            UserEntity ue = conn.getDrq().getUserByEmail(email);
            if (ue == null) {
                //starting and training are BLACK based on the admin role
                BeltEntity be = new BeltEntity("black3");
                RoleEntity re = new RoleEntity("admin");
                conn.getDcq().insertUser(email, fname, lname, pwd, re, be, be);
            } else {
                return Response.failure("Error: User already exists.");
            }

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.success("Success: Admin created.");
    }

    /**
     * Creates an customer user if the user does not already exists in the system.
     * The belt for the customer is automatically assigned white.
     *
     * @param email The email used.
     * @param fname The first name.
     * @param lname The last name.
     * @param pwd   The password.
     * @return A Response which contains a bool that dictates success/failure and the msg on why
     */
    public Response createCustomer(String email,
                                   String fname,
                                   String lname,
                                   String pwd) {

        try {
            //test if the user already exists.
            UserEntity ue = conn.getDrq().getUserByEmail(email);
            if (ue == null) {
                //starting and training are BLACK based on the admin role
                BeltEntity be = new BeltEntity("white");
                RoleEntity re = new RoleEntity("customer");
                conn.getDcq().insertUser(email, fname, lname, pwd, re, be);
                ue = conn.getDrq().getUserByEmail(email);
                setUser(ue);
            } else {
                return Response.failure("Error: User already exists.");
            }

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.success("Success: Customer created.");
    }

    /**
     * Creates an trainer user if the user does not already exists in the system.
     * The trainers belt is its training rank.
     *
     * @param email The email used.
     * @param fname The first name.
     * @param lname The last name.
     * @param pwd   The password.
     * @return A Response which contains a bool that dictates success/failure and the msg on why
     */
    public Response createTrainer(String email, String fname, String lname,
                                  String pwd, BeltEntity trainingRank) {

        try {
            //test if the user already exists.
            UserEntity ue = conn.getDrq().getUserByEmail(email);
            if (ue == null) {
                //starting and training are the training rank
                RoleEntity re = new RoleEntity("trainer");
                conn.getDcq().insertUser(email, fname, lname, pwd, re, trainingRank, trainingRank);
                ue = conn.getDrq().getUserByEmail(email);
                setUser(ue);
            } else {
                return Response.failure("Error: User already exists.");
            }

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.success("Success: Trainer created.");
    }


    /**
     * Creates a group class if a class does not already exist at the starttime,date, and room number.
     *
     * @param rNum           The room number.
     * @param date           The start date.
     * @param sTime          The start time.
     * @param eTime          The end time.
     * @param tEmail         The email of the trainer.
     * @param maxClassSize   The max class size.
     * @param minBelt        The minimum belt required to enter this class.
     * @param createdByEmail The email of who created this course.
     * @return A Response which contains a bool that dictates success/failure and the msg on why
     */
    public Response createGroupClass(int rNum, LocalDate date, double sTime, double eTime,
                                     String tEmail, int maxClassSize, BeltEntity minBelt,
                                     String createdByEmail) {

        try {

            UserEntity ue = conn.getDrq().getUserByEmail(tEmail);

            if (!BeltEntity.checkBeltRank(ue.getBelt().toString(), minBelt.toString())) {
                Response.failure("Error: Trainer rank is too low");
            }

            ArrayList<GymClassEntity> gce =
                conn.getDrq().getAllClassesByDateTime(date, sTime, rNum);

            if (gce.size() == 0) {
                //unique values rNum + sTime should be unique
                conn.getDcq().insertClass(rNum, date, sTime, eTime,
                    tEmail, maxClassSize, minBelt, createdByEmail);
            } else {
                return Response.failure("Error: Class already exists");
            }

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.success("Success: Class created.");

    }

    /**
     * Creates a private class if a class does not already exist at the starttime, date, and room number.
     * The private class method wraps the group class, but hard codes the size to 2. Thats because a private
     * class is by definition a class with only 2 people in it.
     *
     * @param rNum           The room number.
     * @param date           The start date.
     * @param sTime          The start time.
     * @param eTime          The end time.
     * @param tEmail         The email of the trainer.
     * @param minBelt        The minimum belt required to enter this class.
     * @param createdByEmail The email of who created this course.
     * @return A Response which contains a bool that dictates success/failure and the msg on why
     */
    public Response createPrivateClass(int rNum, LocalDate date, double sTime, double eTime,
                                       String tEmail, BeltEntity minBelt,
                                       String createdByEmail) {

        return createGroupClass(rNum, date, sTime, eTime,
            tEmail, 2, minBelt, createdByEmail);
    }

    //DB Encapsulated Read methods /////////////////////////////////////////////////////////

    public Response readGetUser(String email) {
        UserEntity ue = null;
        try {
            ue = conn.getDrq().getUserByEmail(email);
            if (ue == null) {
                return Response.failure("Error: User does not exist");
            }
        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

        return Response.success("Success: User retreived", ue);
    }

    /**
     * Gets just 1 class.
     * @param localDate is the local date
     * @param startTime is the start time
     * @param roomNumber is the room number
     * @return
     */
    public Response readGetClass(LocalDate localDate, double startTime, int roomNumber) {

        try {
            String str = localDate.format(SqlConstants.DBDATEFORMAT);
            GymClassEntity gce = conn.getDrq().getClass(str, startTime, roomNumber);
            if (gce == null) {
                return Response.failure("Error: Class not found.");
            }
            return Response.success("Success: Class found.", gce);

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error");
        }
    }

    //DB Encapsulated Update methods /////////////////////////////////////////////////////////

    /**
     * //TODO
     *
     * @param email
     * @return
     */
    public Response updateUserPassword(String email) {

        UserEntity ue = null;
        try {
            ue = conn.getDrq().getUserByEmail(email);

            if (ue == null) {
                return Response.failure("Error: Cannot delete, user does not exist.");
            }

            conn.getDbd().deleteUser(email);
            ue = conn.getDrq().getUserByEmail(email);

            if (ue == null) {
                return Response.success("Success: User deleted");
            }

            return Response.failure("Error: User is not deleted.");
        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }


    }

    //DB Encapsulated Delete methods /////////////////////////////////////////////////////////

    /**
     * Deletes a user from the database.
     *
     * @param email the email of the user.
     * @return the response.
     */
    public Response deleteUser(String email) {
        UserEntity ue = null;
        try {
            ue = conn.getDrq().getUserByEmail(email);

            if (ue == null) {
                return Response.failure("Error: Cannot delete, user does not exist.");
            }

            conn.getDbd().deleteUser(email);
            ue = conn.getDrq().getUserByEmail(email);

            if (ue == null) {
                return Response.success("Success: User deleted");
            }

            return Response.failure("Error: User is not deleted.");
        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

    }

    /**
     * Deletes 1 classs.
     *
     * @param roomNumber The room number.
     * @param startDate  The start date.
     * @param startTime  The start time.
     * @return
     */
    public Response deleteClass( LocalDate startDate,double startTime,int roomNumber ) {

        try {

            String str = startDate.format(SqlConstants.DBDATEFORMAT);
            conn.getDbd().deleteClass(roomNumber, str, startTime);

            return Response.failure("Success: Class might be deleted.");

        } catch (SQLException ecp) {
            ecp.printStackTrace();
            return Response.failure("Error: SQL error.");
        }

    }

    /**Enroll the user provided into the class with the id passed.
     * @param classId class id of where this user will be added as a student
     * @param userEmail email of the student that will be enrolling
     * @return
     */
    public Response enrollUser(int classId, String userEmail) {
        try {
            conn.getDcq().insertEnrolledUser(classId, userEmail);
        } catch (SQLiteException e) {
            return Response.failure("Student is already enrolled in this class!");
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.failure("Error: could not find class id or useremail");
        }
        return Response.success("Added student");
    }

    public Response getClassesUserEnrolledInByEmail(String userEmail) {
        try {
            return Response.success("Retrieved users enrolled classes",
                    conn.getDrq().getClassesUserEnrolledInByEmail(userEmail));
        } catch (SQLException e) {
            return Response.failure("User does not exist!");
        }
    }

}


