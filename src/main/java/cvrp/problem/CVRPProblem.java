package cvrp.problem;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CVRPProblem {

    private int vehiclesNumber;
    private int maxVehicleCapacity;

    private List<Customer> customers;
    private Depot depot;

    private double[][] distanceMatrix;

    private CVRPProblem() {
    }

    public CVRPProblem(int vehiclesNumber, int maxVehicleCapacity, List<Customer> customers, Depot depot, double[][] distanceMatrix) {
        this.vehiclesNumber = vehiclesNumber;
        this.maxVehicleCapacity = maxVehicleCapacity;
        this.customers = customers;
        this.depot = depot;
        this.distanceMatrix = distanceMatrix;
    }

    public double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public int getVehiclesNumber() {
        return vehiclesNumber;
    }

    public int getMaxVehicleCapacity() {
        return maxVehicleCapacity;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Depot getDepot() {
        return depot;
    }

    @JsonIgnore
    public int getTotalDemand() {
        return customers.stream().map(Customer::getDemand).reduce((a, b) -> a + b).get();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("total number of customers: ").append(this.customers.size()).append("\n");
        stringBuilder.append("capacity of vehicles: ").append(this.maxVehicleCapacity).append("\n");
        stringBuilder.append("number of vehicles: ").append(this.vehiclesNumber).append("\n");
        for (int i = 0; i < this.customers.size(); i++) {
            stringBuilder.append("Node ").append(i + 1).append(" ").append(this.customers.get(i)).append("\n");
        }
        stringBuilder.append("total demanded: ").append(this.getTotalDemand()).append("\n");
        return stringBuilder.toString();
    }
}
