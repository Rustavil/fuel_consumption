package ru.rustavil.fuel_consumption.rest.dto;

import ru.rustavil.fuel_consumption.domain.FuelType;

import java.time.LocalDate;

public class MonthDriverFuelConsumptionResponseDto {

    private LocalDate date;
    private Long driverIdentifier;
    private FuelType fuelType;
    private Double fuelVolume;
    private Double fuelPrice;

    public MonthDriverFuelConsumptionResponseDto() {
    }

    public MonthDriverFuelConsumptionResponseDto(int year, int mont, Long driverIdentifier, FuelType fuelType, Double fuelVolume, Double fuelPrice) {
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
}
