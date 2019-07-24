package ru.rustavil.fuel_consumption.domain;

import java.time.LocalDate;
import java.util.Objects;

public class MonthDriverFuelConsumption {

    private LocalDate date;
    private Long driverIdentifier;
    private FuelType fuelType;
    private Double fuelVolume;
    private Double fuelPrice;

    public MonthDriverFuelConsumption() {
    }

    public MonthDriverFuelConsumption(int year, int mont, Long driverIdentifier, FuelType fuelType, Double fuelVolume, Double fuelPrice) {
        this.date = LocalDate.of(year, mont, 1);
        this.driverIdentifier = driverIdentifier;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDriverIdentifier() {
        return driverIdentifier;
    }

    public void setDriverIdentifier(Long driverIdentifier) {
        this.driverIdentifier = driverIdentifier;
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

    public Double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
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
        return Objects.hash(date, driverIdentifier, fuelType, fuelVolume, fuelPrice);
    }
}
