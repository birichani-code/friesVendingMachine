package com.birichani.code;

public class VendingMachineFactory {
    public static FriesVendingMachine createVendingMachine() {
        return new FriesVendingMachineImpl(); }

}
