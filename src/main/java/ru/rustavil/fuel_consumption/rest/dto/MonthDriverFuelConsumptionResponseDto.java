package ru.rustavil.fuel_consumption.rest.dto;

import ru.rustavil.fuel_consumption.domain.FuelType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthDriverFuelConsumptionResponseDto {

    private LocalDate date;
    private Long driverIdentifier;
    private FuelType fuelType;
    private Double fuelVolume;
    private BigDecimal fuelPrice;

    public MonthDriverFuelConsumptionResponseDto() {
    }

    public MonthDriverFuelConsumptionResponseDto(int year, int mont, Long driverIdentifier,
                                                 FuelType fuelType, Double fuelVolume,
                                                 BigDecimal fuelPrice) {
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

    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(BigDecimal fuelPrice) {
        this.fuelPrice = fuelPrice;
    }
}
