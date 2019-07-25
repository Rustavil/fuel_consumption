package ru.rustavil.fuel_consumption.useCase;

import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;

import java.util.List;
import java.util.UUID;

public interface MonthFuelConsumptionReport {

    List<MonthFuelConsumption> load(int page, int size);

    List<MonthFuelConsumption> load(UUID driverId, int page, int size);
}
