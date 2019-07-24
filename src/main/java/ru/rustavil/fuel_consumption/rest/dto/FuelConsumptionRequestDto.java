package ru.rustavil.fuel_consumption.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FuelConsumptionRequestDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate date;
    @Positive
    private Long driverIdentifier;
    private FuelType fuelType;
    @Positive
    private Double fuelVolume;
    @Positive
    private BigDecimal fuelPrice;

    public FuelConsumptionRequestDto() {
    }

    public FuelConsumptionRequestDto(LocalDate date, @Positive Long driverIdentifier, FuelType fuelType, @Positive Double fuelVolume, @Positive BigDecimal fuelPrice) {
        this.date = date;
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
