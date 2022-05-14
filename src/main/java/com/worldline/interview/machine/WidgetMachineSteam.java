package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.SteamEngine;
import com.worldline.interview.enums.FuelType;

public class WidgetMachineSteam extends WidgetMachine {


    public WidgetMachineSteam(SteamEngine engine) {
        super(engine);
    }

    public double produceWidgets(int quantity) {
        engine.start();
        double cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private double produce(int quantity) {
        int batch = 0;
        int batchCount = 0;
        double costPerBatch = 0;

        if (engine.getFuelType() == FuelType.PETROL) {
            costPerBatch = 9;
        } else if (engine.getFuelType() == FuelType.DIESEL) {
            costPerBatch = 12;
        } else if (engine.getFuelType() == FuelType.WOOD) {
            costPerBatch = 4.35;
        } else if (engine.getFuelType() == FuelType.COAL) {
            costPerBatch = 5.65;
        }

        while (batch < quantity) {
            batch = batch + 2;
            batchCount++;
        }

        return batchCount * costPerBatch;
    }

    public Engine getEngine() {
        return engine;
    }
}
