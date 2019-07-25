package ru.rustavil.fuel_consumption.useCase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.MonthMoneyConsumptionRepository;
import ru.rustavil.fuel_consumption.useCase.MonthMoneyConsumptionReport;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonthMoneyConsumptionReportImpl implements MonthMoneyConsumptionReport {

    private final MonthMoneyConsumptionRepository moneyConsumptionRepository;
    private final DriverRepository driverRepository;

    @Override
    public List<MonthMoneyConsumption> load(int page, int size) {
        return moneyConsumptionRepository.load(page, size);
    }

    @Override
    public List<MonthMoneyConsumption> load(UUID driverId, int page, int size) {
        Driver driver = driverRepository.findById(driverId);
        return moneyConsumptionRepository.load(driver.getId(), page, size);
    }
}
