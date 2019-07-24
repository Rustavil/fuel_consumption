package ru.rustavil.fuel_consumption.useCase;

import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;


public interface FuelPurchaseRegister {

    void registerPurchase(FuelPurchaseRequestDto fuelPurchaseRequestDto);

}
