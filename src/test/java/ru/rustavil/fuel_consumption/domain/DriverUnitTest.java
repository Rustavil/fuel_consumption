package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

public class DriverUnitTest {

    @Test
    public void whenCreateValidDriverThenExpectedSuccess() {
        Driver.builder().identifier(1000L).build();
    }

    @Test(expected = InvalidException.class)
    public void whenCreateDriverWithNegativeDriverIdentifierThenExpected() {
        Driver.builder().identifier(-1000L).build();
    }
}
