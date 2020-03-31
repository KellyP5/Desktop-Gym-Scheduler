package main.java.memoranda.gym;

import java.util.HashMap;

public class ManageUsers {

    private HashMap<String, User> users;

    /**
     * Constructor
     */
    public ManageUsers(){
        this.users = new HashMap<>();
    }

    /**
     * Creates a user, and assigns a starting belt.
     * @param email
     * @param userName
     * @param userPassword
     * @param startingRank
     */
    public void createUser(String email, String userName, String userPassword, Belt startingRank){
        User newUser = new User(userName,email,userPassword);
        assignBelt(email,startingRank);
        users.put(email,newUser);
    }

    /** Sets the password for the given student.
     *
     * @param email
     * @param password
     * @return true on success.
     */
    public boolean setPassword(String email, String password){
        if(users.containsKey(email)){
            users.get(email).setPassword(password);
            return true;
        }
        return false;
    }

    /**
     * Sets the rank for a the given student
     * @param email
     * @param belt
     * @return true on success.
     */
    boolean assignBelt(String email, Belt belt){
        if(this.users.containsKey(email)){
            this.users.get(email).setRank(belt);
            return true;
        }
        return false;
    }

    /**
     * Sets the training rank for the given student.
     * @param email
     * @param belt
     * @return true on success.
     */
    public boolean assignTrainingRank(String email, Belt belt){
        if(this.users.containsKey(email)){
            this.users.get(email).setTrainingRank(belt);
            return true;
        }
        return false;
    }

    /**
     * Returns just a user.
     * @param email
     * @return
     */
    public User getUser(String email){
        return this.users.get(email);
    }


}
