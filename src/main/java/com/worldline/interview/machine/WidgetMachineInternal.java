package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.enums.FuelType;

public class WidgetMachineInternal extends WidgetMachine {

    public WidgetMachineInternal(InternalCombustionEngine engine) {
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
            costPerBatch = 5.65;
        } else if (engine.getFuelType() == FuelType.COAL) {
            costPerBatch = 4.35;
        }

        while (batch < quantity) {
            batch = batch + 8;
            batchCount++;
        }

        return batchCount * costPerBatch;
    }

    public Engine getEngine() {
        return engine;
    }
}
