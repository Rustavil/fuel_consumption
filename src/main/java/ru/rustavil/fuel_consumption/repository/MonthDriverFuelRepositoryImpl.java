package ru.rustavil.fuel_consumption.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.MonthDriverFuelConsumption;
import ru.rustavil.fuel_consumption.domain.repository.MonthDriverFuelRepository;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.MonthDriverFuelConsumptionMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class MonthDriverFuelRepositoryImpl implements MonthDriverFuelRepository {

    private final MonthDriverFuelConsumptionMapper mapper;
    private final FuelConsumptionRepositoryJpa repository;

    @Autowired
    public MonthDriverFuelRepositoryImpl(MonthDriverFuelConsumptionMapper mapper, FuelConsumptionRepositoryJpa repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<MonthDriverFuelConsumption> load(LocalDate month, int page, int size) {
        return mapper.to(repository.calculateMonthDriverFuelConsumptionByMonth(month, PageRequest.of(page, size)));
    }

    @Override
    public List<MonthDriverFuelConsumption> load(LocalDate month, UUID driverId, int page, int size) {
        return mapper.to(repository.calculateMonthDriverFuelConsumptionByMonthAndDriverId(month, driverId, PageRequest.of(page, size)));
    }
}
