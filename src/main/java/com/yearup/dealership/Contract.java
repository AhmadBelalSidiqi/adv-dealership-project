package com.yearup.dealership;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Contract {
    public static ArrayList<Contract> contracts= new ArrayList<>();
    protected final String date;
    protected final String customerName;
    protected final String customerEmail;
    protected Vehicle soldVehicle;
//    protected double totalPrice;
//    protected double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail,Vehicle soldVehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.soldVehicle = soldVehicle;

    }

    public abstract double getMonthlyPayment();
    public abstract double getTotalPrice();
    public abstract String getCsvFormated();


    public void setSoldVehicle(Vehicle soldVehicle) {
        this.soldVehicle = soldVehicle;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getSoldVehicle() {
        return soldVehicle;
    }

    public static boolean isVehicleEligibleForContract(Vehicle vehicle){
        int currentYear = LocalDate.now().getYear();
        int vehicleYearsOld = currentYear-vehicle.getYear();
        return vehicleYearsOld <= 3;
    }
}
