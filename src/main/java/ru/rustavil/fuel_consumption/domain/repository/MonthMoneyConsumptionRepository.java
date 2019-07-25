package ru.rustavil.fuel_consumption.domain.repository;

import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;

import java.util.List;
import java.util.UUID;

public interface MonthMoneyConsumptionRepository {

    List<MonthMoneyConsumption> load(int page, int size);

    List<MonthMoneyConsumption> load(UUID driverId, int page, int size);
}
