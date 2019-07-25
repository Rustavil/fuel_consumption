package ru.rustavil.fuel_consumption.rest.dto;

import lombok.Data;
import ru.rustavil.fuel_consumption.domain.FuelType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
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
}
