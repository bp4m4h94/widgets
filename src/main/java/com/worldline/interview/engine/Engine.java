package com.worldline.interview.engine;

import com.worldline.interview.enums.FuelType;

public interface Engine {

    void start();

    void stop();

    boolean isRunning();

    void fill(FuelType fuelType, int fuelLevel);

    FuelType getFuelType();

    int getFuelLevel();

    FuelType getRequiredFuelType();

}
