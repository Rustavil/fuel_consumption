package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;

public class MonthFuelConsumptionUnitTest {

    @Test
    public void whenCreateValidMontFuelConsumptionThenExpectedSuccess() {
        new MonthFuelConsumption(2019, 7, FuelType.TYPE_95, 1000.0,
                BigDecimal.valueOf(100.0), BigDecimal.valueOf(100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMontFuelConsumptionWithNegativeFuelVolumeThenExpected() {
        new MonthFuelConsumption(2019, 7, FuelType.TYPE_95, -1000.0,
                BigDecimal.valueOf(100.0), BigDecimal.valueOf(100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMontFuelConsumptionWithNegativeAvgFuelPriceThenExpected() {
        new MonthFuelConsumption(2019, 7, FuelType.TYPE_95, 1000.0,
                BigDecimal.valueOf(100.0), BigDecimal.valueOf(-100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMontFuelConsumptionWithNegativeTotalFuelPriceThenExpected() {
        new MonthFuelConsumption(2019, 7, FuelType.TYPE_95, -1000.0,
                BigDecimal.valueOf(-100.0), BigDecimal.valueOf(100.0));
    }
}
