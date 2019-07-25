package ru.rustavil.fuel_consumption.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
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
}
