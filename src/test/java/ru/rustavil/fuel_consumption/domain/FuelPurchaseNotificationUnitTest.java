package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FuelPurchaseNotificationUnitTest {

    @Test
    public void whenCreateFuelPurchaseNotificationWithNegativeTotalFuelVolumeThanExpectedSuccess() {
        new FuelPurchaseNotification(100.0, new HashMap<>(), 100.0);
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelPurchaseNotificationWithNegativeTotalFuelVolumeThanExpected() {
        new FuelPurchaseNotification(-100.0, new HashMap<>(), 100.0);
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelPurchaseNotificationWithNegativeTotalFuelPriceThanExpected() {
        new FuelPurchaseNotification(100.0, new HashMap<>(), -100.0);
    }
    @Test
    public void whenBuildMsgThenExpectedMsg() {
        FuelPurchaseNotification notification = new FuelPurchaseNotification(10.0, new HashMap<FuelType, Double>(){
            {
                put(FuelType.TYPE_D, 10.0);
            }
        }, 100.5);
        assertEquals("Total fuel volume: 10.00L (TYPE_D:10.0). Total price: 100.50EUR", notification.buildMsg());
    }
}
