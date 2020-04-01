package main.java.memoranda.gym;

import org.json.JSONObject;

import java.util.Date;
import java.util.Scanner;

public class Gym {

    public String adminName;
    public String adminPassword;
    public String adminEmail;

    public User user;
    public String userName;
    public String userEmail;

    public boolean isLoggedIn;
    public boolean isAdmin;

    private ManageUsers manageUsers;
    private ManageClasses manageClasses;


    /**
     * Default constructor. Values are hard coded.
     */
    public Gym(){
        this.adminEmail = "admin";
        this.adminName = "admin";
        this.adminPassword = "admin";

        this.manageClasses = new ManageClasses();
        this.manageUsers = new ManageUsers();
    }

    /**
     * Signs into the system using admin email/password
     * @param adminEmail default is default@gym.com
     * @param userPassword default is 1234
     * @return whether successful or not
     */
    public boolean adminLogin(String adminEmail, String userPassword){
        if(this.adminEmail.compareTo(adminEmail)==0){
            if(this.adminPassword.compareTo(userPassword)==0){
                this.isAdmin = true;
                this.isLoggedIn = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Logs into the system
     * @param userEmail
     * @param userPassword
     * @return returns true if successful.
     */
    public boolean userLogin(String userEmail, String userPassword){
        User user = this.manageUsers.getUser(userEmail);
        if(user!=null){
                if(user.checkPassword(userPassword)){
                    this.isLoggedIn = true;
                    this.user = user;
                    return true;
                }
        }
        return false;
    }

    public JSONObject getState(){
        //TODO
        return null;
    }


    //////////////////////////////////////////////////////Methods for covering requirements in topic.pdf


    /**
     * 5. public classes are setup by the owner of the gym and can only be setup when a room
     * is available and a trainer is available (the system should find an available trainer
     * with the appropriate rank)
     */
    public void admin_createClass(int classType, long startTime, Integer duration,Integer roomNumber, Belt beltReq){

        switch(classType){
            case 1:
            {
                //private
                manageClasses.createPrivateClass(new Date(startTime),duration,roomNumber,beltReq);
            }
            case 2:
            {
                //public group class
                manageClasses.createGroupClass(new Date(startTime),duration,roomNumber,beltReq);
            }
        }

    }

    /**
     * 6. the owner can assign him/herself to a class if s/he wants to teach it
     */
    public void admin_assignClassroomTrainer(){
        //TODO
    }

    /**
     * 7. the owner can enter trainers into the system with their rank and training rank
     */
    public void admin_createTrainer(){
        //TODO
    }

    /**
     * 8. the trainer can setup possible private appointment times, these times can be booked
     * by customers.
     *
     * 9. if the owner wants to schedule a class and there is a "possible private appointment"
     * from a trainer with no students enrolled yet, the class is just overwritten.
     *
     * 15. trainers can set times when they are available to teach so the system will only assign
     * them to classes at these times
     *
     * 17. group classes are either advanced on beginner. Students in advanced classes need
     * least need a blue belt.
     *
     * 18. Trainers can always teach all students below their training rank
     *
     * 19. for advanced public classes only black2 training rank students can teach advanced
     * classes.
     *
     */
    public void admin_setupClass(){
        //TODO
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void admin_createUser(){
        //TODO
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void admin_changeUserBelt(){
        //TODO
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void admin_changeUserTrainingBelt(){
        //TODO
    }

    /**
     * 15. trainers can set times when they are available to teach so the system will only assign
     * them to classes at these times
     */
    public void trainer_setAvailableTime(){
        //TODO
    }

    /**
     * Derived from UI.
     */
    public void user_createAccount(){
        //TODO
    }

    /**
     * 11. the students can book private classes that are already setup and are not booked by
     * someone yet, or enroll in public classes.
     *
     * 16. trainers can login as trainers or students, as students they can take normal classes
     */
    public void user_bookClass(){
        //TODO
    }

    /**
     * 13. trainers, owners and students can view their overall and their own schedule at any
     * time
     */
    public void user_viewSchedule(){
        //TODO
    }

    /**
     * 14. all users should be able to store their own notes on things in the tool which should
     * show up after they login
     */
    public void user_storeNote(){
        //TODO this will most likely use the preexisting notes system
    }












}
