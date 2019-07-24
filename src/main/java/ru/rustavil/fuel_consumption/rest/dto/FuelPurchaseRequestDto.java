package ru.rustavil.fuel_consumption.rest.dto;

import java.util.Arrays;
import java.util.List;

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

    public List<FuelConsumptionRequestDto> getFuelConsumptionRequestDtoList() {
        return fuelConsumptionRequestDtoList;
    }

    public void setFuelConsumptionRequestDtoList(List<FuelConsumptionRequestDto> fuelConsumptionRequestDtoList) {
        this.fuelConsumptionRequestDtoList = fuelConsumptionRequestDtoList;
    }
}
