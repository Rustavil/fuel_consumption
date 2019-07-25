package ru.rustavil.fuel_consumption.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
public class MonthDriverFuelConsumptionDto {

    private LocalDate date;
    private Long driverIdentifier;
    private FuelType fuelType;
    private Double fuelVolume;
    @Digits(integer=5, fraction=2)
    private BigDecimal fuelPrice;

    public MonthDriverFuelConsumptionDto() {
    }

    public MonthDriverFuelConsumptionDto(int year, int mont, Long driverIdentifier, FuelType fuelType, Double fuelVolume, BigDecimal fuelPrice) {
        this.date = LocalDate.of(year, mont, 1);
        this.driverIdentifier = driverIdentifier;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
    }
}
