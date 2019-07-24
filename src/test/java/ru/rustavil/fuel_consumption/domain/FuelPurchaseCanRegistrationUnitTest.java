package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.EmptyFuelConsumptionException;
import ru.rustavil.fuel_consumption.domain.exceptions.MinimalFuelVolumeException;
import ru.rustavil.fuel_consumption.domain.exceptions.RequiredDriverException;

import java.math.BigDecimal;

public class FuelPurchaseCanRegistrationUnitTest {

    @Test(expected = RequiredDriverException.class)
    public void requireDriver() {
        FuelPurchase fuelPurchase = new FuelPurchase(new FuelConsumption(null, FuelType.TYPE_95, 10.0, BigDecimal.valueOf(10.0)));
        fuelPurchase.IsCanRegister();
    }

    @Test(expected = EmptyFuelConsumptionException.class)
    public void emptyFuelConsumption() {
        FuelPurchase fuelPurchase = new FuelPurchase();
        fuelPurchase.IsCanRegister();
    }

    @Test(expected = MinimalFuelVolumeException.class)
    public void minimalRegistrationFuelVolume() {
        FuelPurchase fuelPurchase = new FuelPurchase(new FuelConsumption(new Driver(), FuelType.TYPE_95, 0.0, BigDecimal.valueOf(10.0)));
        fuelPurchase.IsCanRegister();
    }
}
