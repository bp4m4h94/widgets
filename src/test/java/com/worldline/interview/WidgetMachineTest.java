package test.java.com.worldline.interview;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.engine.SteamEngine;
import com.worldline.interview.enums.FuelType;
import com.worldline.interview.machine.WidgetMachine;
import com.worldline.interview.machine.WidgetMachineInternal;
import com.worldline.interview.machine.WidgetMachineSteam;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class WidgetMachineTest {

    private WidgetMachine interCombMachine;
    private WidgetMachine steamMachine;
    private Engine engine;

    @Before
    public void setUp() {

        // Machine with internalCombustion engine
        InternalCombustionEngine interCombEngine = new InternalCombustionEngine(FuelType.PETROL);
        interCombEngine.fill(FuelType.PETROL, 20);
        interCombMachine = new WidgetMachineInternal(interCombEngine);

        // Machine with steam engine
        SteamEngine steamEngine = new SteamEngine(FuelType.COAL);
        steamEngine.fill(FuelType.COAL, 1);
        steamMachine = new WidgetMachineSteam(steamEngine);

        // prepare to test steam engine individually
        engine = new SteamEngine(FuelType.COAL);
        engine.fill(FuelType.COAL, 1);
    }


    // It supports only two types of fuel – wood and coal (it cannot be filled with any other type of fuel)
    @Test
    public void givenSteamMachineFuelIsSupported() {
        assertThat(steamMachine.getEngine().getFuelType(), anyOf(equalTo(FuelType.COAL), equalTo(FuelType.WOOD)));
    }

    // The fuel level must be greater than zero
    @Test
    public void checkFuelLevelGreaterThanZero() {
        assertTrue("Fuel Level must greater than 0", steamMachine.getEngine().getFuelLevel() > 0);
    }

    // The engine must have been filled with the required fuel type (engines are always empty when first created)
    @Test
    public void checkRequiredFuelType() {
        assertThat(steamMachine.getEngine().getRequiredFuelType(), anyOf(equalTo(FuelType.COAL), equalTo(FuelType.WOOD)));
    }
    // Engines must be initialised to use one fuel type before being started for the first time
    @Test
    public void checkEngineIsInitialized() {
        assertThat(steamMachine.getEngine().getFuelType(), notNullValue());
    }

    // Modify the Widget Machine to be able to use your new steam engine (it must continue to support the existing engines)
    // In order to start a production run, the Widget Machine must first start the engine and ensure it is running. Once production is finished, it must stop the engine
    @Test
    public void checkEngineStartAndStop() {
        engine.start();
        assertTrue("Steam Engine is not running properly", engine.isRunning());
        engine.stop();
        assertFalse("Steam Engine was not stopped properly", engine.isRunning());
    }

    // Testify if the cost was calculated right after production run
    @Test
    public void costCalculator() {
        assertThat(interCombMachine.produceWidgets(30), equalTo(36.0));
        assertThat(interCombMachine.produceWidgets(90), equalTo(108.0));
        assertThat(steamMachine.produceWidgets(8), equalTo(22.6));
    }


}
