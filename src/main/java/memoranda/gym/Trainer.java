package main.java.memoranda.gym;

public class Trainer extends User{
    private Belt trainingRank;

    /**
     * Constructor for User. Belt's are not instantiated until assigned by the ManageUser class.
     *
     * @param name
     * @param email
     * @param password
     */
    public Trainer(String name, String email, String password,Belt startingRank, Belt trainingRank) {
        super(name, email, password);
        super.setRank(startingRank);
        this.trainingRank = trainingRank;
    }

    public Belt getTrainingRank(){
        return this.trainingRank;
    }

    public void setTrainingRank(Belt newRank){
        this.trainingRank = newRank;
    }
}
