package ru.rustavil.fuel_consumption.domain;

import java.time.LocalDate;
import java.util.Objects;

public class MonthFuelConsumption {

    private LocalDate date;
    private FuelType fuelType;
    private Double fuelVolume;
    private Double totalFuelPrice;
    private Double avgFuelPrice;

    public MonthFuelConsumption() {
    }

    public MonthFuelConsumption(int year, int mont, FuelType fuelType, Double fuelVolume, Double totalFuelPrice, Double avgFuelPrice) {
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

    public Double getTotalFuelPrice() {
        return totalFuelPrice;
    }

    public void setTotalFuelPrice(Double totalFuelPrice) {
        this.totalFuelPrice = totalFuelPrice;
    }

    public Double getAvgFuelPrice() {
        return avgFuelPrice;
    }

    public void setAvgFuelPrice(Double avgFuelPrice) {
        this.avgFuelPrice = avgFuelPrice;
    }
}
