package ru.rustavil.fuel_consumption.useCase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.MonthDriverFuelConsumption;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.MonthDriverFuelRepository;
import ru.rustavil.fuel_consumption.useCase.MonthDriverFuelConsumptionReport;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonthDriverFuelConsumptionReportImpl implements MonthDriverFuelConsumptionReport {

    private final MonthDriverFuelRepository repository;
    private final DriverRepository driverRepository;

    @Override
    public List<MonthDriverFuelConsumption> load(LocalDate month, int page, int size) {
        return repository.load(month, page,size);
    }

    @Override
    public List<MonthDriverFuelConsumption> load(LocalDate month, UUID driverId, int page, int size) {
        Driver driver = driverRepository.findById(driverId);
        return repository.load(month, driver.getId(), page,size);
    }
}
