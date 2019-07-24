package ru.rustavil.fuel_consumption.domain.repository;

import ru.rustavil.fuel_consumption.domain.FuelConsumption;

import java.util.List;

public interface FuelConsumptionRepository {

    FuelConsumption save(FuelConsumption fuelConsumption);
    List<FuelConsumption> save(List<FuelConsumption> fuelConsumptionList);

}
