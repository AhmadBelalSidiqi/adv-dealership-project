package com.yearup.dealership;

public class LeaseContract extends Contract {
    private static final double INTEREST_RATE = 0.04;
    private static final int PERIOD = 36;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle soldVehicle) {
        super(date, customerName, customerEmail, soldVehicle);
    }


    // region getters
    public double getExpectedEndingValue() {
        return soldVehicle.getPrice() * 0.5;
    }

    public double getLeaseFee() {
        return soldVehicle.getPrice() * 0.07;
    }
    // endregion


    @Override
    public String getCsvFormated() {
        return String.format( "LEASE|" + this.date + "|" + this.customerName + "|" + this.customerEmail + "|" + soldVehicle.getCsvFormated() + "|" + getExpectedEndingValue() + "|" + getLeaseFee() + "|%.2f|%.2f",getTotalPrice(),getMonthlyPayment());
    }

    @Override
    public double getTotalPrice() {
        return (getMonthlyPayment() * PERIOD) + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return this.soldVehicle.getPrice() * ((INTEREST_RATE * Math.pow(1 + INTEREST_RATE, PERIOD)) / (Math.pow(1 + INTEREST_RATE, PERIOD) - 1));
    }
}
