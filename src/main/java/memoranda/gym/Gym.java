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

        while(true){
            System.out.println("GymMenu: ");
            System.out.println("1) adminLogin");
            System.out.println("2) userLogin");
            System.out.println("3) exit");

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


    }

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

}
