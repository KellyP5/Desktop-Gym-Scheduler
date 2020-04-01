package main.java.memoranda.gym;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Class {
    public ArrayList<User> users;
    public Date startTime;
    public Integer duration;
    public Integer roomNumber;
    public Integer maxClassSize;
    public Belt beltReq;


    /**
     * Constructor for Class
     * @param startTime Is the start time
     * @param duration is the duration the class will last.
     * @param roomNumber is the assigned room number.
     * @param classSize is the size of the class. Size of 1 indicates the class is a private class.
     */
    public Class(Date startTime, Integer duration, Integer roomNumber, Integer classSize, Belt beltReq){
        this.users = new ArrayList<>();
        this.startTime = startTime;
        this.duration = duration;
        this.roomNumber = roomNumber;
        this.maxClassSize = classSize;
        this.beltReq = beltReq;
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

    /**
     * Adds a user to a class.
     * @param user
     * @return true if successful.
     */
    public boolean addUser(User user){
        if(this.users.size()<this.maxClassSize){
            this.users.add(user);
            return true;
        }
        return false;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return users.equals(aClass.users) &&
                startTime.equals(aClass.startTime) &&
                roomNumber.equals(aClass.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, startTime, duration, roomNumber, maxClassSize);
    }
}
