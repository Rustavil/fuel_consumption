package ru.rustavil.fuel_consumption.useCase;


import ru.rustavil.fuel_consumption.domain.MonthDriverFuelConsumption;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MonthDriverFuelConsumptionReport {

    List<MonthDriverFuelConsumption> load(LocalDate month, int page, int size);
    List<MonthDriverFuelConsumption> load(LocalDate month, UUID driverId, int page, int size);
}
