package ru.rustavil.fuel_consumption.useCase;

import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;

import java.util.List;
import java.util.UUID;

public interface MonthMoneyConsumptionReport {

    List<MonthMoneyConsumption> load(int pgae, int size);
    List<MonthMoneyConsumption> load(UUID driverId, int pgae, int size);

}
