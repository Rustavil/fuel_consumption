package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FuelPurchaseNotificationUnitTest {

    @Test
    public void buildMsg() {
        FuelPurchaseNotification notification = new FuelPurchaseNotification(10.0, new HashMap<FuelType, Double>(){
            {
                put(FuelType.TYPE_D, 10.0);
            }
        }, 100.5);
        assertEquals("Total fuel volume: 10.00L (TYPE_D:10.0). Total price: 100.50EUR", notification.buildMsg());
    }
}
