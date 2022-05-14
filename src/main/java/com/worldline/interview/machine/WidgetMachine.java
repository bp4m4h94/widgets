package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.engine.SteamEngine;

public abstract class WidgetMachine {
    protected Engine engine;

    public WidgetMachine(InternalCombustionEngine engine) {
        this.engine = engine;
    }

    public WidgetMachine(SteamEngine engine) {
        this.engine = engine;
    }

    public double produceWidgets(int quantity) {
        return 0;
    }

    private double produce(int quantity) {
        return 0;
    }

    public Engine getEngine() {
        return engine;
    }
}
