package com.pluralsight;

import java.util.ArrayList;

public class UserInterface {
    private Dealership dealership;


    public UserInterface() {
    }

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }

    public void display() {
        init();
        boolean running = true;
        while (running) {
            String userInput = UserInput.getUserString("""
                    Welcome to our Dealership
                    Please chose one of the following options
                    A) Get Vehicles by price
                    B) Get Vehicles by make and model
                    C) Get Vehicles by year
                    D) Get Vehicles by mileage
                    E) Get Vehicles by vehicle type
                    F) get Vehicles by color
                    G) Get All Vehicles
                    H) Add Vehicles
                    I) Remove Vehicles
                    X) Exit the Program
                    """);

            switch (userInput.toUpperCase()) {
                case "A" -> processGetByPriceRequest();
                case "B" -> processGetByMakeModeRequest();
                case "C" -> processGetByYearRequest();
                case "D" -> processGetByMileageRequest();
                case "E" -> processGetByVehicleTypeRequest();
                case "F" -> processGetByVehicleColor();
                case "G" -> processGetAllVehiclesRequest();
                case "H" -> processAddVehicleRequest();
                case "I" -> processRemoveVehicleRequest();
                case "X" -> running = false;
                default -> System.err.println("Wrong Input.");
            }

        }

    }

    private void processGetByPriceRequest() {
        displayVehicles(dealership.getVehiclesByPrice(
                UserInput.getUserDouble("Please enter the minimum price: "),
                UserInput.getUserDouble("Please enter the maximum price: ")));
    }

    private void processGetByMakeModeRequest() {
        displayVehicles(dealership.getVehiclesByMakeModel(
                UserInput.getUserString("Please enter the make: "),
                UserInput.getUserString("Please enter the model: ")));
    }

    private void processGetByYearRequest() {
        displayVehicles(dealership.getVehiclesByYear(
                UserInput.getUserInteger("Please enter start year: "),
                UserInput.getUserInteger("Please enter end year: ")));
    }

    private void processGetByMileageRequest() {
        displayVehicles(dealership.getVehiclesByMileage(
                UserInput.getUserInteger("Please enter the minimum mileage: "),
                UserInput.getUserInteger("Please enter the maximum mileage: ")));
    }

    private void processGetByVehicleColor() {
        displayVehicles(dealership.getVehiclesByColor(
                UserInput.getUserString("Please enter the color of vehicle: ")));
    }

    private void processGetByVehicleTypeRequest() {
        displayVehicles(dealership.getVehiclesByType(
                UserInput.getUserString("Please enter the type of vehicle: ")));
    }

    private void processGetAllVehiclesRequest() {
        processAllVehiclesRequest();
    }

    private void processAddVehicleRequest() {
        dealership.addVehicle(getVehicle());
        DealershipFileManager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest() {
        int vin = UserInput.getUserInteger("Enter the vin number");
        for(Vehicle vehicle : dealership.getAllVehicle() )
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                DealershipFileManager.saveDealership(dealership);
                return;
            }

    }

    private static void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println("VIN|YEAR|MAKE|MODEL|TYPE|MILEAGE|PRICE");
        for (Vehicle vehicle : vehicles)
            System.out.println(vehicle);
    }

    private Vehicle getVehicle() {
        return new Vehicle(UserInput.getUserInteger("Enter the car vin number: "),
                UserInput.getUserInteger("Enter the year: "),
                UserInput.getUserString("Enter the make: "),
                UserInput.getUserString("Enter the model: "),
                UserInput.getUserString("Enter the type: "),
                UserInput.getUserString("Enter the color: "),
                UserInput.getUserInteger("Enter the mileage: "),
                UserInput.getUserDouble("Enter the price: "));
    }

    public void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicle());
    }


}
