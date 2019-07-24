package ru.rustavil.fuel_consumption.repository.entities;

import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class MonthFuelConsumptionDto {

    private LocalDate date;
    private FuelType fuelType;
    private Double fuelVolume;
    @Digits(integer=5, fraction=2)
    private BigDecimal totalFuelPrice;
    private Double avgFuelPrice;

    public MonthFuelConsumptionDto(int year, int mont, FuelType fuelType, Double fuelVolume, BigDecimal totalFuelPrice, Double avgFuelPrice) {
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

    public Double getAvgFuelPrice() {
        return avgFuelPrice;
    }

    public void setAvgFuelPrice(Double avgFuelPrice) {
        this.avgFuelPrice = avgFuelPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthFuelConsumptionDto that = (MonthFuelConsumptionDto) o;
        return Objects.equals(date, that.date) &&
                fuelType == that.fuelType &&
                Objects.equals(fuelVolume, that.fuelVolume) &&
                Objects.equals(totalFuelPrice, that.totalFuelPrice) &&
                Objects.equals(avgFuelPrice, that.avgFuelPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, fuelType, fuelVolume, totalFuelPrice, avgFuelPrice);
    }
}
