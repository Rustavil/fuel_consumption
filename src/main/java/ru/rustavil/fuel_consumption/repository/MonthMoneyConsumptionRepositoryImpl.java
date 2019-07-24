package ru.rustavil.fuel_consumption.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;
import ru.rustavil.fuel_consumption.domain.repository.MonthMoneyConsumptionRepository;
import ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.MonthMoneyConsumptionMapper;

import java.util.List;
import java.util.UUID;

@Repository
public class MonthMoneyConsumptionRepositoryImpl implements MonthMoneyConsumptionRepository {

    private final MonthMoneyConsumptionMapper mapper;
    private final FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa;

    @Autowired
    public MonthMoneyConsumptionRepositoryImpl(MonthMoneyConsumptionMapper mapper, FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa) {
        this.mapper = mapper;
        this.fuelConsumptionRepositoryJpa = fuelConsumptionRepositoryJpa;
    }

    @Override
    public List<MonthMoneyConsumption> load(int page, int size) {
        Page<MonthMoneyConsumptionDto> result = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumption(PageRequest.of(page, size));
        return mapper.to(result.getContent());
    }

    @Override
    public List<MonthMoneyConsumption> loadByDriverId(UUID driverId, int page, int size) {
        Page<MonthMoneyConsumptionDto> result = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumptionByDriverId(driverId, PageRequest.of(page, size));
        return mapper.to(result.getContent());
    }
}
