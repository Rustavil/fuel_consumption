package ru.rustavil.fuel_consumption.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class FuelConsumption {

    private UUID id;
    private Driver driver;
    private FuelType fuelType;
    private Double fuelVolume;
    private BigDecimal fuelPrice;

    public FuelConsumption() {
        this.id = UUID.randomUUID();
    }

    public FuelConsumption(Driver driver, FuelType fuelType, Double fuelVolume, BigDecimal fuelPrice) {
        this.id = UUID.randomUUID();
        this.driver = driver;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(Double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(BigDecimal fuelPrice) {
        this.fuelPrice = fuelPrice;
    }
}
