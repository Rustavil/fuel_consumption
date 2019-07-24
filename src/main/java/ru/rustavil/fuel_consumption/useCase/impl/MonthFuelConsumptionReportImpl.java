package ru.rustavil.fuel_consumption.useCase.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MonthFuelConsumptionReportImpl implements MonthFuelConsumptionReport {

    private final MonthFuelConsumptionMapper mapper;
    private final MonthFuelConsumptionRepository repository;
    private final DriverRepository driverRepository;

    @Autowired
    public MonthFuelConsumptionReportImpl(MonthFuelConsumptionMapper mapper, MonthFuelConsumptionRepository repository, DriverRepository driverRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<MonthFuelConsumption> load(int page, int size) {
        return mapper.from(repository.load(page, size));
    }

    @Override
    public List<MonthFuelConsumption> loadByDriverId(UUID driverId, int page, int size) {
        Driver driver = driverRepository.findById(driverId);
        return mapper.from(repository.load(driverId, page, size));
    }
}
