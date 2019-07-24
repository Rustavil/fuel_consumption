package ru.rustavil.fuel_consumption.domain;

import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class FuelConsumption {

    private final UUID id;
    private final Driver driver;
    private final FuelType fuelType;
    private final Double fuelVolume;
    private final BigDecimal fuelPrice;

    public FuelConsumption(UUID id,
                           Driver driver,
                           FuelType fuelType,
                           Double fuelVolume,
                           BigDecimal fuelPrice) {
        this.id = id;
        if (Objects.isNull(driver)) {
            throw new InvalidException("Driver required");
        }
        this.driver = driver;
        this.fuelType = fuelType;
        if (fuelVolume <= 0) {
            throw new InvalidException("Fuel volume must be great than 0");
        }
        this.fuelVolume = fuelVolume;
        if (fuelPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidException("Fuel price must be great than 0");
        }
        this.fuelPrice = fuelPrice;
    }

    public FuelConsumption(Driver driver,
                           FuelType fuelType,
                           Double fuelVolume,
                           BigDecimal fuelPrice) {
        this.id = UUID.randomUUID();
        if (Objects.isNull(driver)) {
            throw new InvalidException("Driver required");
        }
        this.driver = driver;
        this.fuelType = fuelType;
        if (fuelVolume <= 0) {
            throw new InvalidException("Fuel volume must be great than 0");
        }
        this.fuelVolume = fuelVolume;
        if (fuelPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidException("Fuel price must be great than 0");
        }
        this.fuelPrice = fuelPrice;
    }

    public UUID getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
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
}
