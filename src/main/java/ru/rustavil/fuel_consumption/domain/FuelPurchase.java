package ru.rustavil.fuel_consumption.domain;


import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FuelPurchase {

    private final List<FuelConsumption> fuelConsumptionList;

    public FuelPurchase(FuelConsumption fuelConsumption) {
        if (Objects.isNull(fuelConsumption)) {
            throw new InvalidException("Fuel consumption empty");
        }
        this.fuelConsumptionList = Collections.singletonList(fuelConsumption);
    }

    public FuelPurchase(List<FuelConsumption> fuelConsumptionList) {
        if (Objects.isNull(fuelConsumptionList) || fuelConsumptionList.isEmpty()) {
            throw new InvalidException("Fuel consumption empty");
        }
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

}
