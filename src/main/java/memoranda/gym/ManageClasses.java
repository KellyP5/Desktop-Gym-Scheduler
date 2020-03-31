package main.java.memoranda.gym;

import java.util.ArrayList;
import java.util.Date;

public class ManageClasses {
    private ArrayList<Class> classes;

    public ManageClasses(){
        this.classes = new ArrayList<>();
    }

    public boolean createGroupClass(Date startTime,Integer duration, Integer roomNumber ){
        //TODO
        return false;
    }

    public boolean createPrivateClass(Date startTime,Integer duration, Integer roomNumber ){
        //TODO
        return false;
    }

    public Class getClass(Date startTime, Integer roomNumber){
        //TODO
        return null;
    }

    public ArrayList<Class> getClasses(){
        //TODO
        return null;
    }

    public ArrayList<User> getUsers(Date startTime, Integer roomNumber){
        //TODO
        return null;
    }

    public boolean assignTrainer(String email, Class _class){
        //TODO
        return false;
    }

}
