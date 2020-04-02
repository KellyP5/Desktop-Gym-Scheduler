package main.java.memoranda.gym;

public class Belt {

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

    public Belt(Rank rank){
        this.rank = rank;
    }

}
