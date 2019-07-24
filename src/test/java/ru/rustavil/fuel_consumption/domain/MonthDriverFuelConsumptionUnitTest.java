package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;

public class MonthDriverFuelConsumptionUnitTest {

    @Test
    public void whenCreateValidMonthDriverFuelConsumptionFuelVolumeThenExpectedSuccess(){
        new MonthDriverFuelConsumption(2019, 7, 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMonthDriverFuelConsumptionWithNegativeFuelVolumeThenExpected(){
        new MonthDriverFuelConsumption(2019, 7, 11111L, FuelType.TYPE_95, -100.0, BigDecimal.valueOf(100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMonthDriverFuelConsumptionWithNegativeFuelPriceThenExpected(){
        new MonthDriverFuelConsumption(2019, 7, 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(-100.0));
    }
}
