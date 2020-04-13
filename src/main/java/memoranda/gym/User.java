package main.java.memoranda.gym;

public class User {

    private String name;
    private String email;
    private String password;

    private Belt rank;

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
     * Assigns the rank.
     * @param rank
     */
    public void setRank(Belt rank){
        this.rank = rank;
    }

}
