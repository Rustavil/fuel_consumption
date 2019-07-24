package ru.rustavil.fuel_consumption.domain;


import ru.rustavil.fuel_consumption.domain.exceptions.EmptyFuelConsumptionException;
import ru.rustavil.fuel_consumption.domain.exceptions.MinimalFuelVolumeException;
import ru.rustavil.fuel_consumption.domain.exceptions.RequiredDriverException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FuelPurchase {

    private static final long MIN_REGISTRATION_FUEL_VOLUME = 1;
    private List<FuelConsumption> fuelConsumptionList;

    public FuelPurchase() {
    }

    public FuelPurchase(FuelConsumption fuelConsumption) {
        this.fuelConsumptionList = Collections.singletonList(fuelConsumption);
    }

    public FuelPurchase(List<FuelConsumption> fuelConsumptionList) {
        this.fuelConsumptionList = fuelConsumptionList;
    }

    public List<FuelConsumption> getFuelConsumptionList() {
        return fuelConsumptionList;
    }

    public Double getTotalFuelVolume() {
        return this.fuelConsumptionList.stream().mapToDouble(FuelConsumption::getFuelVolume).sum();
    }

    public Map<FuelType, Double> getFuelVolumeMap() {
        return this.fuelConsumptionList.stream().collect(Collectors.toMap(FuelConsumption::getFuelType, FuelConsumption::getFuelVolume, Double::sum, LinkedHashMap::new));
    }

    public BigDecimal getTotalPrice() {
        return this.fuelConsumptionList.stream().map(FuelConsumption::getFuelPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void IsCanRegister() {
        if (Objects.isNull(this.fuelConsumptionList) || this.fuelConsumptionList.isEmpty()) {
            throw new EmptyFuelConsumptionException();
        }
        for (FuelConsumption fuelConsumption: this.fuelConsumptionList) {
            if (Objects.isNull(fuelConsumption.getDriver())) {
                throw new RequiredDriverException();
            }
            if (fuelConsumption.getFuelVolume() < MIN_REGISTRATION_FUEL_VOLUME) {
                throw new MinimalFuelVolumeException(String.format("Minimal registration volume %d", MIN_REGISTRATION_FUEL_VOLUME));
            }
        }
    }

}
