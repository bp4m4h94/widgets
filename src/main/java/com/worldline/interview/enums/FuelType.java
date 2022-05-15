package com.worldline.interview.enums;

public enum FuelType {
    PETROL(9),
    DIESEL(12),
    WOOD(4.35),
    COAL(5.65);

    private double costPerBatch;

    FuelType(double costPerBatch) {
        this.costPerBatch = costPerBatch;
    }

    public double getCostPerBatch() {
        return costPerBatch;
    }
}
