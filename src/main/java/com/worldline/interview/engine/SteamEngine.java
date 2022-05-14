package com.worldline.interview.engine;

import com.worldline.interview.enums.FuelType;

public class SteamEngine implements Engine {

    private boolean running;
    private int fuelLevel;
    private final FuelType requiredFuelType;
    private FuelType fuelType;

    public SteamEngine(FuelType requiredFuelType) {
        this.requiredFuelType = requiredFuelType;
        running = false;
        fuelLevel = 0;
    }

    @Override
    public void start() {
        if (fuelLevel > 0 && requiredFuelType.equals(fuelType)) {
            running = true;
        } else {
            throw new IllegalStateException("Not able to start engine.");
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void fill(FuelType fuelType, int fuelLevel) {
        this.fuelLevel = fuelLevel;
        this.fuelType = fuelType;
    }

    @Override
    public FuelType getFuelType() {
        return requiredFuelType;
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public FuelType getRequiredFuelType() {
        return requiredFuelType;
    }
}
