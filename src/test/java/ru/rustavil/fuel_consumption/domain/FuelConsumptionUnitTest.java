package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;

public class FuelConsumptionUnitTest {

    @Test(expected = InvalidException.class)
    public void whenCreateValidFuelConsumptionThenExpectedSuccess(){
        new FuelConsumption(null, FuelType.TYPE_95, 1000.0, BigDecimal.valueOf(1000L));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithoutDriverThenExpected(){
        new FuelConsumption(null, FuelType.TYPE_95, 1000.0, BigDecimal.valueOf(1000L));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithNegativeFuelVolumeThenExpected(){
        new FuelConsumption(new Driver(11111L), FuelType.TYPE_95, -1000.0, BigDecimal.valueOf(1000L));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithNegativeFuelPriceThenExpected(){
        new FuelConsumption(new Driver(11111L), FuelType.TYPE_95, 1000.0, BigDecimal.valueOf(-1000L));
    }
}
