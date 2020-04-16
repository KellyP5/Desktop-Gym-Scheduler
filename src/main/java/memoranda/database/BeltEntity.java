package main.java.memoranda.database;

import java.util.Objects;

/*
Belt Entity is the entity used for all Belt related SQL
 */
public class BeltEntity {
    @Override
    public String toString() {
        String ret = "";
        switch(this.rank){
            case white:
            {
                ret += "white";
                break;
            }
            case yellow:
            {
                ret += "yellow";
                break;
            }
            case orange:
            {
                ret += "orange";
                break;
            }
            case purple:
            {
                ret += "purple";
                break;
            }
            case blue:
            {
                ret += "blue";
                break;
            }
            case blue_stripe:
            {
                ret += "blue_stripe";
                break;
            }
            case green:
            {
                ret += "green";
                break;
            }
            case green_stripe:
            {
                ret += "green_stripe";
                break;
            }
            case brown1:
            {
                ret += "brown1";
                break;
            }
            case brown2:
            {
                ret += "brown2";
                break;
            }
            case brown3:
            {
                ret += "brown3";
                break;
            }
            case black1:
            {
                ret += "black1";
                break;
            }
            case black2:
            {
                ret += "black2";
                break;
            }
            case black3:
            {
                ret += "black3";
                break;
            }
        }
        return ret;
    }

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
