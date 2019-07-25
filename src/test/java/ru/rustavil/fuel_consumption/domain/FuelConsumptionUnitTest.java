package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;

public class FuelConsumptionUnitTest {

    @Test
    public void whenCreateValidFuelConsumptionThenExpectedSuccess(){
        FuelConsumption.builder().driver(Driver.builder().identifier(11111L).build()).fuelType(FuelType.TYPE_95).fuelVolume(1000.0).fuelPrice(BigDecimal.valueOf(1000L)).build();
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithoutDriverThenExpected(){
        FuelConsumption.builder().fuelType(FuelType.TYPE_95).fuelVolume(1000.0).fuelPrice(BigDecimal.valueOf(1000L)).build();
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithNegativeFuelVolumeThenExpected(){
        FuelConsumption.builder().driver(Driver.builder().identifier(11111L).build()).fuelType(FuelType.TYPE_95).fuelVolume(-1000.0).fuelPrice(BigDecimal.valueOf(1000L)).build();
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelConsumptionWithNegativeFuelPriceThenExpected(){
        FuelConsumption.builder().driver(Driver.builder().identifier(11111L).build()).fuelType(FuelType.TYPE_95).fuelVolume(1000.0).fuelPrice(BigDecimal.valueOf(-1000L)).build();
    }
}
