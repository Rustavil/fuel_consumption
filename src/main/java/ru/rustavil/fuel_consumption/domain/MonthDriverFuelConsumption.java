package ru.rustavil.fuel_consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MonthDriverFuelConsumption {

    private final LocalDate date;
    private final Long driverIdentifier;
    private final FuelType fuelType;
    private final Double fuelVolume;
    private final BigDecimal fuelPrice;

    public MonthDriverFuelConsumption(
            int year, int mont, Long driverIdentifier, FuelType fuelType,
            Double fuelVolume, BigDecimal fuelPrice) {
        this.date = LocalDate.of(year, mont, 1);
        this.driverIdentifier = driverIdentifier;
        this.fuelType = fuelType;
        if (fuelVolume < 0) {
            throw new InvalidException("Total fuel volume must not be negative");
        }
        this.fuelVolume = fuelVolume;
        if (fuelPrice.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new InvalidException("Total fuel price must not be negative");
        }
        this.fuelPrice = fuelPrice;
    }

    public static MonthDriverFuelConsumptionBuilder builder() {
        return new ValidateMonthDriverFuelConsumptionBuilder();
    }

    private static class ValidateMonthDriverFuelConsumptionBuilder extends MonthDriverFuelConsumptionBuilder {

        @Override
        public MonthDriverFuelConsumption build() {
            if (super.fuelVolume < 0) {
                throw new InvalidException("Total fuel volume must not be negative");
            }
            if (super.fuelPrice.compareTo(BigDecimal.ZERO) < 0 ) {
                throw new InvalidException("Total fuel price must not be negative");
            }
            return super.build();
        }

    }
}
