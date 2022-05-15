package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.engine.SteamEngine;

import java.math.BigDecimal;

public abstract class WidgetMachine {
    protected Engine engine;

    public WidgetMachine(InternalCombustionEngine engine) {
        this.engine = engine;
    }

    public WidgetMachine(SteamEngine engine) {
        this.engine = engine;
    }

    public BigDecimal produceWidgets(int quantity) {
        return null;
    }

    private BigDecimal produce(int quantity) {
        return null;
    }

    public Engine getEngine() {
        return engine;
    }
}
