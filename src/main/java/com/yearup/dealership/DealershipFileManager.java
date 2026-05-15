package com.yearup.dealership;

import java.io.*;

public class DealershipFileManager {

    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    public static Dealership getDealership() {
        Dealership dealership;
        Vehicle vehicle;
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            dealership = createDealershipObject(currentLine);
            while ((currentLine = bufferedReader.readLine()) != null) {
                vehicle = createVehicle(currentLine);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        } catch (IOException e) {
            System.err.println("Wrong file location: " + FILE_PATH + " " + e);
        }
        return null;

    }

    //DONE : Create the method

    public static void saveDealership(Dealership dealerShip) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(dealerShip.getName() + "|" + dealerShip.getAddress() + "|" + dealerShip.getPhone());
            for (Vehicle vehicle : dealerShip.getAllVehicle()) {
                bufferedWriter.newLine();
                bufferedWriter.write(vehicle.getCsvFormated());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private static Dealership createDealershipObject(String line) {
        String[] nameAddressNumber = line.split("\\|");
        String name = nameAddressNumber[0];
        String address = nameAddressNumber[1];
        String phoneNumber = nameAddressNumber[2];

        return new Dealership(name, address, phoneNumber);

    }

    private static Vehicle createVehicle(String currentLine) {
        String[] lineSpilt = currentLine.split("\\|");
        int vin = Integer.parseInt(lineSpilt[0]);
        int year = Integer.parseInt(lineSpilt[1]);
        String make = lineSpilt[2];
        String model = lineSpilt[3];
        String type = lineSpilt[4];
        String color = lineSpilt[5];
        int odometer = Integer.parseInt(lineSpilt[6]);
        double price = Double.parseDouble(lineSpilt[7]);
        return new Vehicle(vin, year, make, model, type, color, odometer, price);
    }
}
