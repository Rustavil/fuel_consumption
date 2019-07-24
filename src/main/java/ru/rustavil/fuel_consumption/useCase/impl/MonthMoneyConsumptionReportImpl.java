package ru.rustavil.fuel_consumption.useCase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.MonthMoneyConsumptionRepository;
import ru.rustavil.fuel_consumption.useCase.MonthMoneyConsumptionReport;

import java.util.List;
import java.util.UUID;

@Service
public class MonthMoneyConsumptionReportImpl implements MonthMoneyConsumptionReport {

    private final MonthMoneyConsumptionRepository moneyConsumptionRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public MonthMoneyConsumptionReportImpl(MonthMoneyConsumptionRepository moneyConsumptionRepository, DriverRepository driverRepository) {
        this.moneyConsumptionRepository = moneyConsumptionRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<MonthMoneyConsumption> load(int page, int size) {
        return moneyConsumptionRepository.load(page, size);
    }

    @Override
    public List<MonthMoneyConsumption> loadByDriverId(UUID driverId, int page, int size) {
        Driver driver = driverRepository.findById(driverId);
        return moneyConsumptionRepository.loadByDriverId(driver.getId(), page, size);
    }
}
