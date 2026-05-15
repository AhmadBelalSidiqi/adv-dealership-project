package com.yearup.dealership;

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
        double userMin = UserInput.getUserDouble("Please enter the minimum price: ");
        double userMax = UserInput.getUserDouble("Please enter the maximum price: ");
        displayVehicles(dealership.getVehiclesByPrice(userMin, userMax));
    }

    private void processGetByMakeModeRequest() {
        String userMake = UserInput.getUserString("Please enter the make: ");
        String userModel = UserInput.getUserString("Please enter the model: ");
        displayVehicles(dealership.getVehiclesByMakeModel(userMake, userModel));
    }

    private void processGetByYearRequest() {
        int userStartYear = UserInput.getUserInteger("Please enter start year: ");
        int userEndYear = UserInput.getUserInteger("Please enter end year: ");
        displayVehicles(dealership.getVehiclesByYear(userStartYear, userEndYear));
    }

    private void processGetByMileageRequest() {
        int userMin = UserInput.getUserInteger("Please enter the minimum mileage: ");
        int userMax = UserInput.getUserInteger("Please enter the maximum mileage: ");
        displayVehicles(dealership.getVehiclesByMileage(userMin, userMax));
    }

    private void processGetByVehicleColor() {
        String userColor = UserInput.getUserString("Please enter the color of vehicle: ");
        displayVehicles(dealership.getVehiclesByColor(userColor));
    }

    private void processGetByVehicleTypeRequest() {
        String userType = UserInput.getUserString("Please enter the type of vehicle: ");
        displayVehicles(dealership.getVehiclesByType(userType));
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
