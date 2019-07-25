package ru.rustavil.fuel_consumption.useCase;

import ru.rustavil.fuel_consumption.domain.FuelPurchase;
import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;


public interface FuelPurchaseRegister {

    FuelPurchase registerPurchase(FuelPurchaseRequestDto fuelPurchaseRequestDto);

}
