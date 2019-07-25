package ru.rustavil.fuel_consumption.useCase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.MonthFuelConsumptionRepository;
import ru.rustavil.fuel_consumption.repository.mapper.MonthFuelConsumptionMapper;
import ru.rustavil.fuel_consumption.useCase.MonthFuelConsumptionReport;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonthFuelConsumptionReportImpl implements MonthFuelConsumptionReport {

    private final MonthFuelConsumptionRepository repository;
    private final DriverRepository driverRepository;

    @Override
    public List<MonthFuelConsumption> load(int page, int size) {
        return repository.load(page, size);
    }

    @Override
    public List<MonthFuelConsumption> load(UUID driverId, int page, int size) {
        Driver driver = driverRepository.findById(driverId);
        return repository.load(driver.getId(), page, size);
    }
}
