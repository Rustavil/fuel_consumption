package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

public class DriverUnitTest {

    @Test
    public void whenCreateValidDriverThenExpectedSuccess() {
        new Driver(1000L);
    }

    @Test(expected = InvalidException.class)
    public void whenCreateDriverWithNegativeDriverIdentifierThenExpected() {
        new Driver(-1000L);
    }
}
