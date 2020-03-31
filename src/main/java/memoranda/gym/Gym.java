package main.java.memoranda.gym;

import org.json.JSONObject;

public class Gym {

    private String adminName;
    private String adminPassword;
    private String adminEmail;

    private ManageUsers manageUsers;
    private ManageClasses manageClasses;

    /**
     * CLI will be replaced by the UI.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("hello world");
        //TODO
    }

    /**
     * Default constructor. Values are hard coded.
     */
    public Gym(){
        this.adminEmail = "default@gym.com";
        this.adminName = "admin";
        this.adminPassword = "1234";

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
                return true;
            }
        }
        return false;
    }

    public boolean userLogin(String userEmail, String userPassword){
        //TODO
        return false;
    }

    public JSONObject getState(){
        //TODO
        return null;
    }

}
