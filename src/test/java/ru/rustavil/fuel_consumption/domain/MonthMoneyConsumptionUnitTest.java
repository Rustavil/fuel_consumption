package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthMoneyConsumptionUnitTest {

    @Test
    public void whenCreateValidMonthMoneyConsumptionThenExpectedSuccess() {
        new MonthMoneyConsumption(LocalDate.now(), BigDecimal.valueOf(100.0));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateMonthMoneyConsumptionWithNegativeThenExpected() {
        new MonthMoneyConsumption(LocalDate.now(), BigDecimal.valueOf(-100.0));
    }
}
