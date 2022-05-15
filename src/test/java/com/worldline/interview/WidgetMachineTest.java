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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class WidgetMachineTest {

    private WidgetMachine interCombMachine;
    private WidgetMachine steamMachinePowerCoal;
    private WidgetMachine steamMachinePowerWood;
    private Engine engine;

    @Before
    public void setUp() {

        // Create internalCombustion engine powered by petrol
        InternalCombustionEngine interCombEngine = new InternalCombustionEngine(Arrays.asList(FuelType.PETROL, FuelType.DIESEL));
        interCombEngine.fill(FuelType.PETROL, 20);
        interCombMachine = new WidgetMachineInternal(interCombEngine);

        // Create steam engine powered by coal
        SteamEngine steamEnginePowerCoal = new SteamEngine(Arrays.asList(FuelType.WOOD, FuelType.COAL));
        steamEnginePowerCoal.fill(FuelType.COAL, 1);

        // Create steam engine powered by wood
        SteamEngine steamEnginePowerWood = new SteamEngine(Arrays.asList(FuelType.WOOD, FuelType.COAL));
        steamEnginePowerWood.fill(FuelType.WOOD, 345);

        // Create Machine by installing steam engine
        steamMachinePowerCoal = new WidgetMachineSteam(steamEnginePowerCoal);
        steamMachinePowerWood = new WidgetMachineSteam(steamEnginePowerWood);

        // prepare to test steam engine individually
        engine = new SteamEngine(Arrays.asList(FuelType.WOOD, FuelType.COAL));
        engine.fill(FuelType.COAL, 1);
    }


    // It supports only two types of fuel – wood and coal (it cannot be filled with any other type of fuel)
    @Test
    public void givenSteamMachineFuelIsSupported() {
        assertThat(steamMachinePowerCoal.getEngine().getFuelType(), anyOf(equalTo(FuelType.COAL), equalTo(FuelType.WOOD)));
    }

    // The fuel level must be greater than zero
    @Test
    public void checkFuelLevelGreaterThanZero() {
        assertTrue("Fuel Level must greater than 0", steamMachinePowerCoal.getEngine().getFuelLevel() > 0);
    }

    // The engine must have been filled with the required fuel type (engines are always empty when first created)
    @Test
    public void checkRequiredFuelType() {
        assertThat(steamMachinePowerCoal.getEngine().getRequiredFuelType(), allOf(hasItems(FuelType.COAL), hasItems(FuelType.WOOD)));
    }
    // Engines must be initialised to use one fuel type before being started for the first time
    @Test
    public void checkEngineIsInitialized() {
        assertThat(steamMachinePowerCoal.getEngine().getFuelType(), notNullValue());
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

        // cost of machine run by steam engine
        assertThat(steamMachinePowerCoal.produceWidgets(8), equalTo(formatBigDecimal(22.6)));
        assertThat(steamMachinePowerCoal.produceWidgets(3568), equalTo(formatBigDecimal(10079.6)));
        assertThat(steamMachinePowerWood.produceWidgets(234565), equalTo(formatBigDecimal(510181.05)));

        // odd number of quantity
        assertThat(steamMachinePowerCoal.produceWidgets(673755), equalTo(formatBigDecimal(1903360.7)));
        // another type of fuel
        assertThat(steamMachinePowerWood.produceWidgets(673755), equalTo(formatBigDecimal(1465419.3)));

        // switch engine still can work
        assertThat(interCombMachine.produceWidgets(30), equalTo(formatBigDecimal(36)));
        assertThat(interCombMachine.produceWidgets(90), equalTo(formatBigDecimal(108)));

    }


    private BigDecimal formatBigDecimal(double expected) {
        return BigDecimal.valueOf(expected).setScale(3, RoundingMode.HALF_DOWN).stripTrailingZeros();
    }

}
