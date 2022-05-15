package com.worldline.interview.engine;

import com.worldline.interview.enums.FuelType;

import java.util.List;

public class SteamEngine implements Engine {

    private boolean running;
    private int fuelLevel;
    private final List<FuelType> requiredFuelType;
    private FuelType fuelType;

    public SteamEngine(List<FuelType> requiredFuelType) {
        this.requiredFuelType = requiredFuelType;
        running = false;
        fuelLevel = 0;
    }

    @Override
    public void start() {
        if (fuelLevel > 0 && requiredFuelType.contains(fuelType)) {
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
        return fuelType;
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public List<FuelType> getRequiredFuelType() {
        return requiredFuelType;
    }
}
