package ru.rustavil.fuel_consumption.domain.repository;

import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;

import java.util.List;
import java.util.UUID;

public interface MonthFuelConsumptionRepository {

    List<MonthFuelConsumptionDto> load(int page, int size);
    List<MonthFuelConsumptionDto> load(UUID driverId, int page, int size);
}
