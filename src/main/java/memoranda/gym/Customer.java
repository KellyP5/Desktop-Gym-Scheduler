package main.java.memoranda.gym;

public class Customer extends User {

    /**
     * Constructor for User. Belt's are not instantiated until assigned by the ManageUser class.
     *
     * @param name
     * @param email
     * @param password
     */
    public Customer(String name, String email, String password, Belt startingRank) {
        super(name, email, password);
        super.setRank(startingRank);
    }
}
