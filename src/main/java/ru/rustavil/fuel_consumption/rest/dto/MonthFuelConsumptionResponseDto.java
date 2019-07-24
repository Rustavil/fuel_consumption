package ru.rustavil.fuel_consumption.rest.dto;

import ru.rustavil.fuel_consumption.domain.FuelType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthFuelConsumptionResponseDto {

    private LocalDate date;
    private FuelType fuelType;
    private Double fuelVolume;
    private BigDecimal totalFuelPrice;
    private BigDecimal avgFuelPrice;

    public MonthFuelConsumptionResponseDto() {
    }

    public MonthFuelConsumptionResponseDto(int year, int mont,
                                           FuelType fuelType, Double fuelVolume,
                                           BigDecimal totalFuelPrice, BigDecimal avgFuelPrice) {
        this.date = LocalDate.of(year, mont, 1);
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.totalFuelPrice = totalFuelPrice;
        this.avgFuelPrice = avgFuelPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public BigDecimal getTotalFuelPrice() {
        return totalFuelPrice;
    }

    public void setTotalFuelPrice(BigDecimal totalFuelPrice) {
        this.totalFuelPrice = totalFuelPrice;
    }
}
