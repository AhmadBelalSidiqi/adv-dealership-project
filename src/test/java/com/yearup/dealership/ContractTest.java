package com.yearup.dealership;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    @Test
    void isVehicleEligibleForContract() {
        Vehicle vehicle = new Vehicle(111,2023,"test","test","test","test",1,1);
        boolean isEligible = Contract.isVehicleEligibleForContract(vehicle);
        assertTrue(isEligible);
    }
}