package main.java.memoranda.gym;

import java.util.ArrayList;
import java.util.Date;

public class Class {
    ArrayList<User> users;
    Date startTime;
    Integer duration;
    Integer roomNumber;
    Integer maxClassSize;

    /**
     * Constructor for Class
     * @param startTime Is the start time
     * @param duration is the duration the class will last.
     * @param roomNumber is the assigned room number.
     * @param classSize is the size of the class. Size of 1 indicates the class is a private class.
     */
    public Class(Date startTime, Integer duration, Integer roomNumber, Integer classSize){
        this.users = new ArrayList<>();
        this.startTime = startTime;
        this.duration = duration;
        this.roomNumber = roomNumber;
        this.maxClassSize = classSize;
    }

    /**
     * Getters for the users. Should only be used to display the details.
     * @return
     */
    public ArrayList<User> getUsers(){
        return this.users;
    }

    /**
     * This function assumes there is only 1 trainer assigned per class.
     * @return the trainer of the class, otherwise returns null if trainer not assigned.
     */
    public User getTrainer(){

        for(int i = 0;i< this.users.size();i++){
            if(this.users.get(i).isTrainer()){
                return this.users.get(i);
            }
        }
        return null;
    }

    /** Checks whether the class has a trainer assigned.
     *
     * @return whether or not there is a trainer assigned.
     */
    public boolean hasTrainer(){
        if(this.getTrainer()!=null){
            return true;
        }
        return false;
    }

    /**
     * Getter for startTime
     * @return
     */
    public Date getTime(){
        return this.startTime;
    }

    /**
     * Getter for roomNumber.
     * @return
     */
    public Integer getRoomNumber(){
        return this.roomNumber;
    }




}
