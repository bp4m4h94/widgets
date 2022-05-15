package com.worldline.interview.engine;

import com.worldline.interview.enums.FuelType;

import java.util.List;

public interface Engine {

    void start();

    void stop();

    boolean isRunning();

    void fill(FuelType fuelType, int fuelLevel);

    FuelType getFuelType();

    int getFuelLevel();

    List<FuelType> getRequiredFuelType();

}
