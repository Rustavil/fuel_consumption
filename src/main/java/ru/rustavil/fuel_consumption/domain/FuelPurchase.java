package ru.rustavil.fuel_consumption.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@Getter
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

    public static FuelPurchaseBuilder builder() {
        return new ValidateFuelPurchaseBuilder();
    }

    private static class ValidateFuelPurchaseBuilder extends FuelPurchaseBuilder {

        @Override
        public FuelPurchase build() {
            if (Objects.isNull(super.fuelConsumptionList) || super.fuelConsumptionList.isEmpty()) {
                throw new InvalidException("Fuel consumption empty");
            }
            return super.build();
        }

    }
}
