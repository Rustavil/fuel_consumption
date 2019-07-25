package ru.rustavil.fuel_consumption.domain.repository;

import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;

import java.util.List;
import java.util.UUID;

public interface MonthFuelConsumptionRepository {

    List<MonthFuelConsumption> load(int page, int size);
    List<MonthFuelConsumption> load(UUID driverId, int page, int size);
}
