package main.java.memoranda.gym;

import org.json.JSONObject;

import java.util.Scanner;

public class Gym {

    private String adminName;
    private String adminPassword;
    private String adminEmail;

    private boolean isLoggedIn;
    private boolean isAdmin;

    private ManageUsers manageUsers;
    private ManageClasses manageClasses;

    /**
     * CLI will be replaced by the UI.
     * @param args
     */
    public static void main(String[] args) {
        new Gym().run();
    }

    public void run(){

        Scanner scanner = new Scanner(System.in);

        //login loop
        while(true){

            user_print_loginMenu();

            String input = scanner.next();

            switch(input){
                case "1":
                {
                    System.out.println("adminLogin");

                    System.out.print("Email: ");
                    String email = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    this.adminLogin(email,password);
                    break;
                }
                case "2":
                {
                    System.out.println("userLogin");

                    System.out.print("Email: ");
                    String email = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    this.userLogin(email,password);
                    break;
                }
                case "3":
                {
                    return;
                }
            }

            if(isLoggedIn)
                break;
        }

        //main gym loop
        while(true){

        }

    }

    //////////////////////////////////////////////////////Test printing

    void user_print_loginMenu(){
        System.out.println("GymMenu: - default admin is admin/admin");
        System.out.println("1) adminLogin");
        System.out.println("2) userLogin");
        System.out.println("3) exit");
    }

    void admin_print_menu(){
        System.out.println("AdminMenu:");
        System.out.println("1) adminLogin");

    }

    void trainer_print_enu(){
        System.out.println("TrainerMenu: - default admin is admin/admin");
        System.out.println("1) adminLogin");

    }

    void user_print_menu(){
        System.out.println("UserMenu: - default admin is admin/admin");
        System.out.println("1) adminLogin");
    }

    //////////////////////////////////////////////////////End Test printing




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
                    return true;
                }
        }
        return false;
    }

    public JSONObject getState(){
        //TODO
        return null;
    }

    /**
     * 5. public classes are setup by the owner of the gym and can only be setup when a room
     * is available and a trainer is available (the system should find an available trainer
     * with the appropriate rank)
     */
    public void admin_createClass(){
        //TODO
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
