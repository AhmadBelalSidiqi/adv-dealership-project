package com.yearup.dealership;

import java.util.ArrayList;

public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    private final ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }
    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory){
            double price = vehicle.getPrice();
            if (price <= max && price >= min)
                result.add(vehicle);
        }
        return result;

    }

    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory) {
            if (make.equalsIgnoreCase(vehicle.getMake()) && model.equalsIgnoreCase(vehicle.getModel()))
                result.add(vehicle);
        }
        return result;
    }

    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory){
            if (vehicle.getYear() >= min && vehicle.getYear() <= max)
                result.add(vehicle);
        }
        return result;    }

    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory){
            if (vehicle.getColor().equalsIgnoreCase(color))
                result.add(vehicle);
        }
        return result;    }

    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory){
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max)
                result.add(vehicle);
        }
        return result;    }

    // DONE: Create the method
    public ArrayList<Vehicle> getVehiclesByType(String type){
        ArrayList<Vehicle> result = new ArrayList<>();
        for( Vehicle vehicle : this.inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(type))
                result.add(vehicle);
        }
        return result;    }

    public Vehicle getVehicleByVin(int vin){
        for(Vehicle vehicle : this.inventory)
            if (vehicle.getVin() == vin)
                return vehicle;
        return null;
    }

    // DONE:
    public ArrayList<Vehicle> getAllVehicle(){
        return new ArrayList<>(this.inventory);
    }

    // DONE: Create the method
    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    // DONE: Create the method
    public void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    }


    // region getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    // endregion
}
