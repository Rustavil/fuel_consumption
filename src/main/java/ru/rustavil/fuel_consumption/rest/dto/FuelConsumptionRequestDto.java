package ru.rustavil.fuel_consumption.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
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

}
