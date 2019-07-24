package ru.rustavil.fuel_consumption.domain;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class FuelPurchaseTest {

    private List<FuelConsumption> fuelConsumptionList;
    private Map<String, Double> expectedVolumeMap;
    private Double expectedTotalFuelVolume;
    private Double expectedTotalPrice;

    public FuelPurchaseTest(List<FuelConsumption> fuelConsumptionList, Map<String, Double> expectedVolumeMap, Double expectedTotalFuelVolume, Double expectedTotalPrice) {
        this.fuelConsumptionList = fuelConsumptionList;
        this.expectedVolumeMap = expectedVolumeMap;
        this.expectedTotalFuelVolume = expectedTotalFuelVolume;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        Collections.singletonList(new FuelConsumption(new Driver(), FuelType.TYPE_D, 10.0, BigDecimal.valueOf(10.0))),
                        new HashMap<FuelType, Double>(){
                            {
                                put(FuelType.TYPE_D, 10.0);
                            }
                        }, 10.0, 10.0
                },
                {
                        Arrays.asList(
                                new FuelConsumption(new Driver(), FuelType.TYPE_D, 10.0, BigDecimal.valueOf(20.0)),
                                new FuelConsumption(new Driver(), FuelType.TYPE_D, 15.0, BigDecimal.valueOf(25.0))),
                        new HashMap<FuelType, Double>(){
                            {
                                put(FuelType.TYPE_D, 25.0);
                            }
                        }, 25.0, 45.0
                },
                {
                        Arrays.asList(
                                new FuelConsumption(new Driver(), FuelType.TYPE_D, 10.0, BigDecimal.valueOf(20.0)),
                                new FuelConsumption(new Driver(), FuelType.TYPE_D, 15.0, BigDecimal.valueOf(25.0)),
                                new FuelConsumption(new Driver(), FuelType.TYPE_95, 15.0, BigDecimal.valueOf(25.0)),
                                new FuelConsumption(new Driver(), FuelType.TYPE_98, 15.0, BigDecimal.valueOf(25.0))),
                        new HashMap<FuelType, Double>(){
                            {
                                put(FuelType.TYPE_D, 25.0);
                                put(FuelType.TYPE_95, 15.0);
                                put(FuelType.TYPE_98, 15.0);
                            }
                        }, 55.0, 95.0
                }
        });
    }


    @Test
    public void getTotalFuelVolume() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getTotalFuelVolume()).isEqualTo(this.expectedTotalFuelVolume);
    }

    @Test
    public void getFuelVolumeMap() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getFuelVolumeMap()).isEqualTo(this.expectedVolumeMap);
    }

    @Test
    public void getTotalPrice() {
        FuelPurchase fuelPurchase = new FuelPurchase(this.fuelConsumptionList);
        assertThat(fuelPurchase.getTotalPrice()).isEqualTo(BigDecimal.valueOf(this.expectedTotalPrice));
    }
}
