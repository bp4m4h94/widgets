package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.SteamEngine;

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

        while (batch < quantity) {
            batch = batch + 2;
            batchCount++;
        }

        return batchCount * engine.getFuelType().getCostPerBatch();
    }

    public Engine getEngine() {
        return engine;
    }
}
