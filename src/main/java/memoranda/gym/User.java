package main.java.memoranda.gym;

public class User {

    private String name;
    private String email;
    private String password;

    private Belt rank;
    private Belt trainingRank;

    /**
     * Constructor for User. Belt's are not instantiated until assigned by the ManageUser class.
     * @param name
     * @param email
     * @param password
     */
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;

        this.rank = null;
        this.trainingRank = null;
    }

    /**
     * Getter for email.
     * @return
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Getter for name.
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Test whether a user is a trainer or not.
     * @return true if a trainer.
     */
    public boolean isTrainer(){
        if(this.trainingRank==null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Compares password to verify credential.
     * @param password password to check
     * @return true if correct password.
     */
    public boolean checkPassword(String password){
        if(this.password.compareTo(password)==0){
            return true;
        }
        return false;
    }

    /**
     * Assigns the training rank.
     * @param trainingRank
     */
    public void setTrainingRank(Belt trainingRank){
        this.trainingRank = trainingRank;
    }

    /**
     * Assigns the rank.
     * @param rank
     */
    public void setRank(Belt rank){
        this.rank = rank;
    }

}
