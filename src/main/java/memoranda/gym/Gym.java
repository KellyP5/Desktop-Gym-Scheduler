package main.java.memoranda.gym;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Gym {

    public String adminName;
    public String adminPassword;
    public String adminEmail;

    public User user;

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

    /**
     * //TODO need to decide on what type of format we are
     * //TODO going to store the information, or if we are
     * //TODO going to use something like sqllite
     * @return
     */
    public JSONObject getState(){
        //TODO
        return null;
    }


    //////////////////////////////////////////////////////Methods for covering requirements in topic.pdf

    /**
     *
     * 5. public classes are setup by the owner of the gym and can only be setup when a room
     * is available and a trainer is available (the system should find an available trainer
     * with the appropriate rank)
     *
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
     * @param classType 1 is private  2 is public/group. Private classes have a max size of 1, whereas public
     *                  or group classes have a size of 20.
     * @param startTime uses UNIX milliseconds https://currentmillis.com/
     * @param duration will be used to set a block of time for instance, 30 - 60 minute sessions.
     * @param roomNumber the number
     * @param beltReq belt requirement for the class
     */
    public void createClass(int classType, long startTime, Integer duration,Integer roomNumber, Belt beltReq){

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
    public void assignClassroomTrainer(String email,Date startTime, Integer roomNumber){

        User user = manageUsers.getUser(email);
        Class _class = manageClasses.getClass(startTime,roomNumber);

        manageClasses.assignTrainer(user,_class);
    }

    /**
     * 7. the owner can enter trainers into the system with their rank and training rank
     */
    public void createTrainer(String email, String userName, String pw, Belt trainingRank){
        manageUsers.createUser(email,userName,pw,trainingRank);
        manageUsers.assignTrainingRank(email,trainingRank);
    }

    /**
     * 13. trainers, owners and students can view their overall and their own schedule at any
     * time
     */
    public ArrayList<Class> getClasses(){
        return manageClasses.getClasses();
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void createAccount(String email, String userName, String pw, Belt startingRank){
        manageUsers.createUser(email,userName,pw,startingRank);
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void changeUserBelt(String userEmail, Belt newRank){
        this.manageUsers.assignBelt(userEmail,newRank);
    }

    /**
     * 10. the owner can login and enter students and trainers, change their belt color, training
     * rank and everything that makes sense
     */
    public void changeUserTrainingBelt(String userEmail, Belt newRank){
        this.manageUsers.assignTrainingRank(userEmail,newRank);
    }

    /**
     *  TODO not completed!
     *
     * 15. trainers can set times when they are available to teach so the system will only assign
     * them to classes at these times
     */
    public void setAvailableTime(){
        //TODO we need to determine what this method is going to look like based on the UI
        //TODO Possibily need to create Date start and Date end  in each user, and allow this
        //TODO method to assign those values if the user is a trainer.
    }

    /**
     * Derived from UI.
     */
    public void userCreateAccount(String email, String userName, String pw){
        Belt startingBelt = new Belt(Belt.Rank.white);
        createAccount(email,userName,pw,startingBelt);
    }

    /**
     * 11. the students can book private classes that are already setup and are not booked by
     * someone yet, or enroll in public classes.
     *
     * 16. trainers can login as trainers or students, as students they can take normal classes
     */
    public void userBookClass(String email, Date startTime, Integer roomNumber){

        User user = manageUsers.getUser(email);
        if(user==null){
            //TODO if users doesn't exist then we should handle this error
            return;
        }
        Class _class = manageClasses.getClass(startTime,roomNumber);
        manageClasses.bookClass(user, _class);
    }

    /**
     * 14. all users should be able to store their own notes on things in the tool which should
     * show up after they login
     */
    public void user_storeNote(){
        //TODO this will most likely use the preexisting notes system
    }












}
