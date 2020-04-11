package main.java.memoranda.database;

public class BeltEntity {
    public enum Rank{
        white,
        yellow,
        orange,
        purple,
        blue,
        blue_stripe,
        green,
        green_stripe,
        brown1,
        brown2,
        brown3,
        black1,
        black2,
        black3
    }
    public Rank rank;
    public BeltEntity(Rank rank){
        this.rank = rank;
    }
}
