package com.worldline.interview;

public class WidgetMachine {
    private InternalCombustionEngine engine = new InternalCombustionEngine(FuelType.PETROL);

    public int produceWidgets(int quantity) {
        engine.start();
        int cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private int produce(int quantity) {
        int batch = 0;
        int batchCount = 0;
        int costPerBatch = 0;

        if (engine.getFuelType() == FuelType.PETROL) {
            costPerBatch = 9;
        } else if (engine.getFuelType() == FuelType.DIESEL) {
            costPerBatch = 12;
        }

        while (batch < quantity) {
            batch = batch + 8;
            batchCount++;
        }

        return batchCount * costPerBatch;
    }


}
