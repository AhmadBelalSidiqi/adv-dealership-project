package com.yearup.dealership;

import java.time.LocalDate;
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
                    J) Purchase A Vehicle
                    K) Lease A Vehicle
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
                case "J" -> processPurchaseVehicle();
                case "K" -> processLeaseVehicle();
                case "X" -> running = false;
                default -> System.err.println("Wrong Input.");
            }

        }

    }

    private void processLeaseVehicle() {
        String customerName = UserInput.getUserString("Enter your Name: ");
        String customerEmail = UserInput.getUserString("Enter you email: ");
        int vehicleVin = UserInput.getUserInteger("Enter the car Vin");
        String date = String.valueOf(LocalDate.now());
        Vehicle soldVehicle = getVehicleAndRemoveFromDealership(vehicleVin);
        if (!Contract.isVehicleEligibleForContract(soldVehicle)) {
            System.err.println("Sorry We Can not Sale the Vehicle");
            return;
        }
        System.out.println(soldVehicle);
        LeaseContract leaseContract = new LeaseContract(date,customerName,customerEmail,soldVehicle);
        System.out.println("Lease Contract created successfully");
        ContractFileManager.saveContract(leaseContract);


    }

    private void processPurchaseVehicle() {
        String customerName = UserInput.getUserString("Enter your Name: ");
        String customerEmail = UserInput.getUserString("Enter you email: ");
        int vehicleVin = UserInput.getUserInteger("Enter the car Vin");
        String date = String.valueOf(LocalDate.now());
        Vehicle soldVehicle = getVehicleAndRemoveFromDealership(vehicleVin);
        if (!Contract.isVehicleEligibleForContract(soldVehicle)) {
            System.err.println("Sorry We Can not Sale the Vehicle");
            return;
        }
        System.out.println(soldVehicle);
        boolean isFinance = UserInput.getBooleanYesNo("Do you want to Finance the car (Yes/No): ");
        SalesContract salesContract = new SalesContract(date,customerName,customerEmail,soldVehicle,isFinance);
        System.out.println("Sales Contract created successfully");
        ContractFileManager.saveContract(salesContract);
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
        for (Vehicle vehicle : dealership.getAllVehicle())
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                DealershipFileManager.saveDealership(dealership);
                return;
            }

    }

    private Vehicle getVehicleAndRemoveFromDealership(int vin) {
        Vehicle soldVehicle = dealership.getVehicleByVin(vin);
        if (soldVehicle == null) {
            System.err.println("A Vehicle with such Vin: "+vin+" does not exit");
            return null;
        }
        dealership.removeVehicle(soldVehicle);
        DealershipFileManager.saveDealership(dealership);
        return soldVehicle;

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
