package main.java.memoranda.gym;

public class Admin extends Trainer {

    /**
     * Constructor for Admin. Admin is instantiated with the highest training rank, and rank.
     *
     * @param name
     * @param email
     * @param password
     */
    public Admin(String name, String email, String password) {
        super(name, email, password,new Belt(Belt.Rank.black3),new Belt(Belt.Rank.black3));
    }
}
