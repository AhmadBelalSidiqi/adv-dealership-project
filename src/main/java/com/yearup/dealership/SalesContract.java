package com.yearup.dealership;

public class SalesContract extends Contract {
    private static final double SALES_TAX = 1.05;
    private static final int RECORDING_FEE = 100;
    private final boolean isFinance;



    public SalesContract(String date, String customerName, String customerEmail, Vehicle soldVehicle, boolean isFinance) {
        super(date, customerName, customerEmail, soldVehicle);
        this.isFinance = isFinance;
    }

    private double getInterestRate(Vehicle soldVehicle) {
        if (soldVehicle.getPrice() >= 10000)
            return 0.0425;
        return 0.0525;
    }

    private int getProcessingFee(Vehicle soldVehicle) {
        if (soldVehicle.getPrice() < 10000)
            return 295;
        return 495;
    }

    @Override
    public String getCsvFormated() {
        String financeOption = "No";
        if (this.isFinance)
            financeOption = "Yes";
        return String.format("SALE|" + this.date + "|" + this.customerName + "|" + this.customerEmail + "|" + soldVehicle.getCsvFormated() + "|" + SALES_TAX + "|%.2f|" + financeOption + "|%.2f",getTotalPrice(),getMonthlyPayment());
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinance)
            return 0;
        // Monthly payment  formula = principle*((monthlyRate*pow((1+monthlyRate)),period))/(pow((1+r),period)-1)
        double monthlyRate = getInterestRate(this.soldVehicle)/12;
        int period = 24;
        if (this.soldVehicle.getPrice() > 10000)
            period = 48;

        return getTotalPrice() * ((monthlyRate * Math.pow(1 + monthlyRate, period)) / (Math.pow(1 + monthlyRate, period) - 1));

    }

    @Override
    public double getTotalPrice() {
        return (this.soldVehicle.getPrice() + RECORDING_FEE + getProcessingFee(soldVehicle)) * SALES_TAX;
    }
}
