package ru.rustavil.fuel_consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MonthFuelConsumption {

    private final LocalDate date;
    private final FuelType fuelType;
    private final Double fuelVolume;
    private final BigDecimal totalFuelPrice;
    private final BigDecimal avgFuelPrice;

    public MonthFuelConsumption(int year, int mont,
                                FuelType fuelType, Double fuelVolume,
                                BigDecimal totalFuelPrice, BigDecimal avgFuelPrice) {
        this.date = LocalDate.of(year, mont, 1);
        this.fuelType = fuelType;
        if (fuelVolume < 0) {
            throw new InvalidException("Fuel volume must not be negative");
        }
        this.fuelVolume = fuelVolume;
        if (totalFuelPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidException("Total fuel price must not be negative");
        }
        this.totalFuelPrice = totalFuelPrice;
        if (avgFuelPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidException("Fuel avg price must not be negative");
        }
        this.avgFuelPrice = avgFuelPrice;
    }

    public static MonthFuelConsumptionBuilder builder() {
        return new ValidateMonthFuelConsumptionBuilder();
    }

    private static class ValidateMonthFuelConsumptionBuilder extends MonthFuelConsumptionBuilder {

        @Override
        public MonthFuelConsumption build() {
            if (super.fuelVolume < 0) {
                throw new InvalidException("Fuel volume must not be negative");
            }
            if (super.totalFuelPrice.compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidException("Total fuel price must not be negative");
            }
            if (super.avgFuelPrice.compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidException("Fuel avg price must not be negative");
            }
            return super.build();
        }

    }
}
