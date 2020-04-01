package main.java.memoranda.gym;

import java.util.ArrayList;
import java.util.Date;

public class ManageClasses {
    private ArrayList<Class> classes;

    public ManageClasses(){
        this.classes = new ArrayList<>();
    }

    /**
     * Creates a group class.
     * @param startTime
     * @param duration
     * @param roomNumber
     * @return true if successful.
     */
    public boolean createGroupClass(Date startTime,Integer duration, Integer roomNumber,Belt beltReq){
        Class newClass = new Class(startTime,duration,roomNumber,20,beltReq);
        if(!classes.contains(newClass)){
            classes.add(newClass);
            return true;
        }
        return false;
    }

    /**
     * Creates a private class.
     * @param startTime
     * @param duration
     * @param roomNumber
     * @return true on being successful.
     */
    public boolean createPrivateClass(Date startTime,Integer duration, Integer roomNumber,Belt beltReq){
        Class newClass = new Class(startTime,duration,roomNumber,1,beltReq);
        if(!classes.contains(newClass)){
            classes.add(newClass);
            return true;
        }
        return false;
    }

    /**
     * Gets a class.
     * @param startTime
     * @param roomNumber
     * @return returns null if unsuccessful
     */
    public Class getClass(Date startTime, Integer roomNumber){
        for(int i = 0;i< classes.size();i++){
            if(classes.get(i).getTime().compareTo(startTime)==0)
                if(classes.get(i).getRoomNumber().equals(roomNumber))
                    return classes.get(i);
        }
        return null;
    }

    /**
     * gets all the classes.
     * @return
     */
    public ArrayList<Class> getClasses(){
        return this.classes;
    }

    /**
     * Gets all the users from a particular class.
     * @param startTime
     * @param roomNumber
     * @return
     */
    public ArrayList<User> getUsers(Date startTime, Integer roomNumber){
        Class _class = getClass(startTime,roomNumber);
        return _class.getUsers();
    }

    /**
     * Assigns a trainer to a class.
     * @param user
     * @param _class
     * @return true on success.
     */
    public boolean assignTrainer(User user, Class _class){
        for(int i = 0;i< this.classes.size();i++){
            if(this.classes.get(i).equals(_class)){
                this.classes.get(i).addUser(user);
                return true;
            }
        }
        return false;
    }

}
