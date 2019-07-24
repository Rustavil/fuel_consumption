package ru.rustavil.fuel_consumption.domain;

import org.junit.Before;
import org.junit.Test;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FuelPurchaseUnitTest {

    private List<FuelConsumption> fuelConsumptionList;

    @Before
    public void setUp() throws Exception {
        fuelConsumptionList = Arrays.asList(
                new FuelConsumption(new Driver(11111L), FuelType.TYPE_D, 10.0, BigDecimal.valueOf(20.0)),
                new FuelConsumption(new Driver(11111L), FuelType.TYPE_D, 15.0, BigDecimal.valueOf(25.0)),
                new FuelConsumption(new Driver(11111L), FuelType.TYPE_95, 15.0, BigDecimal.valueOf(25.0)),
                new FuelConsumption(new Driver(11111L), FuelType.TYPE_98, 15.0, BigDecimal.valueOf(25.0)));
    }

    @Test
    public void whenCreateValidFuelPurchaseThenExpectedSuccess(){
        new FuelPurchase(new FuelConsumption(new Driver(11111L), FuelType.TYPE_98, 15.0, BigDecimal.valueOf(25.0)));
        new FuelPurchase(Collections.singletonList(new FuelConsumption(new Driver(11111L), FuelType.TYPE_98, 15.0, BigDecimal.valueOf(25.0))));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelPurchaseWithNullThenExpectedSuccess(){
        FuelConsumption fuelConsumption = null;
        new FuelPurchase(fuelConsumption);
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelPurchaseWithEmptyListThenExpectedSuccess(){
        new FuelPurchase(new LinkedList<>());
    }

    @Test
    public void whenInvokeGetTotalFuelVolumeThenExpectedFuelVolumeSum() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getTotalFuelVolume()).isEqualTo(55.0);
    }

    @Test
    public void whenInvokeGetFuelVolumeMapThanExpected() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getFuelVolumeMap()).isEqualTo(new HashMap<FuelType, Double>(){
            {
                put(FuelType.TYPE_D, 25.0);
                put(FuelType.TYPE_95, 15.0);
                put(FuelType.TYPE_98, 15.0);
            }
        });
    }

    @Test
    public void whenInvokeGetTotalPriceVolumeThenExpectedFuelPriceSum() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getTotalPrice()).isEqualTo(BigDecimal.valueOf(95.0));
    }
}
