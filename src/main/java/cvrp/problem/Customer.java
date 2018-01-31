package cvrp.problem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Klientas
 */
public class Customer {

    //eilės numeris naudojamas genuose
    private int number;
    //koordinatės
    private Point point;
    //poreikis
    private int demand;

    public Customer(int number, Point point) {
        this.number = number;
        this.point = point;
        this.demand = demand;
    }

    @JsonCreator
    public Customer(@JsonProperty("number") int number, @JsonProperty("point") Point point, @JsonProperty("demand") int demand) {
        this.number = number;
        this.point = point;
        this.demand = demand;
    }

    public int getNumber() {
        return number;
    }

    public Point getPoint() {
        return point;
    }

    public int getDemand() {
        return demand;
    }

    public Customer withDemand(int demand) {
        return new Customer(number, point, demand);
    }
}
