package ru.rustavil.fuel_consumption.rest.dto;

import lombok.Data;
import ru.rustavil.fuel_consumption.domain.FuelType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
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
}
