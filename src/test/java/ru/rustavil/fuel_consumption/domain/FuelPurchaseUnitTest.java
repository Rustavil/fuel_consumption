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
                FuelConsumption.builder().
                        driver(Driver.builder().identifier(11111L).build()).
                        fuelType(FuelType.TYPE_D).
                        fuelVolume(10.0).
                        fuelPrice(BigDecimal.valueOf(20.0)).
                        build(),

                FuelConsumption.builder().
                        driver(Driver.builder().identifier(11111L).build()).
                        fuelType(FuelType.TYPE_D).
                        fuelVolume(15.0).
                        fuelPrice(BigDecimal.valueOf(25.0)).
                        build(),

                FuelConsumption.builder().
                        driver(Driver.builder().identifier(11111L).build()).
                        fuelType(FuelType.TYPE_95).
                        fuelVolume(15.0).
                        fuelPrice(BigDecimal.valueOf(25.0)).
                        build(),

                FuelConsumption.builder().
                        driver(Driver.builder().identifier(11111L).build()).
                        fuelType(FuelType.TYPE_98).
                        fuelVolume(15.0).
                        fuelPrice(BigDecimal.valueOf(25.0)).
                        build());
    }

    @Test
    public void whenCreateValidFuelPurchaseThenExpectedSuccess() {
        new FuelPurchase(
                Collections.singletonList(
                        FuelConsumption.builder().
                                driver(Driver.builder().identifier(11111L).build()).
                                fuelType(FuelType.TYPE_98).
                                fuelVolume(15.0).
                                fuelPrice(BigDecimal.valueOf(25.0)).
                                build()));
        new FuelPurchase(Collections.singletonList(
                FuelConsumption.builder().
                        driver(Driver.builder().identifier(11111L).build()).
                        fuelType(FuelType.TYPE_98).
                        fuelVolume(15.0).
                        fuelPrice(BigDecimal.valueOf(25.0)).
                        build()));
    }

    @Test(expected = InvalidException.class)
    public void whenCreateFuelPurchaseWithEmptyListThenExpectedSuccess() {
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
        assertThat(fuelPurchase.getFuelVolumeMap()).isEqualTo(new HashMap<FuelType, Double>() {
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
