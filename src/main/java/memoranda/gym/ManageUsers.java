package main.java.memoranda.gym;

import java.util.HashMap;

public class ManageUsers {
    HashMap<String, User> users;

    public ManageUsers(){
        this.users = new HashMap<>();
    }

    public boolean createUser(String userName, String userPassword){
        //TODO
        return false;
    }

    public boolean setPassword(String email, String password){
        //TODO
        return false;
    }

    boolean assignBelt(String email, String password){
        //TODO
        return false;
    }

    public boolean assignTrainingRank(String email, Belt belt){
        //TODO
        return false;
    }

    public User getUser(String email){
        //TODO
        return null;
    }


}
