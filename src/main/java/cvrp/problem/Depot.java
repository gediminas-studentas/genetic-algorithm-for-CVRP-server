package cvrp.problem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Sandelis
 */
public class Depot {

    //eilės numeris naudojamas genuose
    private final int number;
    //koordinatės
    private final Point point;

    @JsonCreator
    public Depot(@JsonProperty("number") int number, @JsonProperty("point") Point point) {
        this.number = number;
        this.point = point;
    }

    public int getNumber() {
        return number;
    }

    public Point getPoint() {
        return point;
    }
}
