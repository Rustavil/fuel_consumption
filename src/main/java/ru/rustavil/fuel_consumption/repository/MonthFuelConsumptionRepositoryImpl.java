package ru.rustavil.fuel_consumption.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;
import ru.rustavil.fuel_consumption.domain.repository.MonthFuelConsumptionRepository;
import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.MonthFuelConsumptionMapper;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MonthFuelConsumptionRepositoryImpl implements MonthFuelConsumptionRepository {

    private final MonthFuelConsumptionMapper mapper;
    private final FuelConsumptionRepositoryJpa fuelConsumptionRepository;

    @Override
    public List<MonthFuelConsumption> load(int page, int size) {
        Page<MonthFuelConsumptionDto> result = fuelConsumptionRepository.calculateMonthFuelConsumptionByMonth(PageRequest.of(page,size));
        return mapper.from(result.getContent());
    }

    @Override
    public List<MonthFuelConsumption> load(UUID driverId, int page, int size) {
        Page<MonthFuelConsumptionDto> result = fuelConsumptionRepository.calculateMonthFuelConsumptionByMonth(PageRequest.of(page,size));
        return mapper.from(result.getContent());
    }
}
