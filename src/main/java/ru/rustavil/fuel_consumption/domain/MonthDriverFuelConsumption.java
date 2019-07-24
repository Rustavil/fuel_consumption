package ru.rustavil.fuel_consumption.domain;

import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    public LocalDate getDate() {
        return date;
    }

    public Long getDriverIdentifier() {
        return driverIdentifier;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthDriverFuelConsumption that = (MonthDriverFuelConsumption) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(driverIdentifier, that.driverIdentifier) &&
                fuelType == that.fuelType &&
                Objects.equals(fuelVolume, that.fuelVolume) &&
                Objects.equals(fuelPrice, that.fuelPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, driverIdentifier,
                fuelType, fuelVolume, fuelPrice);
    }
}
