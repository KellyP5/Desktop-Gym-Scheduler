package main.java.memoranda.database;

import java.util.Objects;

/*
Belt Entity is the entity used for all Belt related SQL
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeltEntity that = (BeltEntity) o;
        return rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }
}
