package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.SteamEngine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WidgetMachineSteam extends WidgetMachine {

    public WidgetMachineSteam(SteamEngine engine) {
        super(engine);
    }

    public BigDecimal produceWidgets(int quantity) {
        engine.start();
        BigDecimal cost = new BigDecimal(0);

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private BigDecimal produce(int quantity) {
        int batch = 0;
        int batchCount = 0;

        while (batch < quantity) {
            batch = batch + 2;
            batchCount++;
        }

        BigDecimal costPerBatch = BigDecimal.valueOf(engine.getFuelType().getCostPerBatch())
                .setScale(3, RoundingMode.HALF_DOWN);

        return costPerBatch.multiply(new BigDecimal(batchCount)).stripTrailingZeros();
    }

    public Engine getEngine() {
        return engine;
    }
}
