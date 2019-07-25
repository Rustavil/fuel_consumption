package ru.rustavil.fuel_consumption.repository;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MonthMoneyConsumptionRepositoryImpl implements MonthMoneyConsumptionRepository {

    private final MonthMoneyConsumptionMapper mapper;
    private final FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa;

    @Override
    public List<MonthMoneyConsumption> load(int page, int size) {
        Page<MonthMoneyConsumptionDto> result = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumption(PageRequest.of(page, size));
        return mapper.to(result.getContent());
    }

    @Override
    public List<MonthMoneyConsumption> load(UUID driverId, int page, int size) {
        Page<MonthMoneyConsumptionDto> result = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumptionByDriverId(driverId, PageRequest.of(page, size));
        return mapper.to(result.getContent());
    }
}
