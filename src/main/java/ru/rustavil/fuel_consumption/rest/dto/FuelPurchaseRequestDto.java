package ru.rustavil.fuel_consumption.rest.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FuelPurchaseRequestDto {

    private List<FuelConsumptionRequestDto> fuelConsumptionRequestDtoList;

    public FuelPurchaseRequestDto() {
    }

    public FuelPurchaseRequestDto(FuelConsumptionRequestDto fuelConsumptionRequestDto) {
        this.fuelConsumptionRequestDtoList = Arrays.asList(fuelConsumptionRequestDto);
    }

    public FuelPurchaseRequestDto(List<FuelConsumptionRequestDto> fuelConsumptionRequestDtoList) {
        this.fuelConsumptionRequestDtoList = fuelConsumptionRequestDtoList;
    }
}
