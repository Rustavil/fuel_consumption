package ru.rustavil.fuel_consumption.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.repository.MonthFuelConsumptionRepository;
import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;

import java.util.List;
import java.util.UUID;

@Repository
public class MonthFuelConsumptionRepositoryImpl implements MonthFuelConsumptionRepository {

    private final FuelConsumptionRepositoryJpa fuelConsumptionRepository;

    @Autowired
    public MonthFuelConsumptionRepositoryImpl(FuelConsumptionRepositoryJpa fuelConsumptionRepository) {
        this.fuelConsumptionRepository = fuelConsumptionRepository;
    }

    @Override
    public List<MonthFuelConsumptionDto> load(int page, int size) {
        Page<MonthFuelConsumptionDto> result = fuelConsumptionRepository.calculateMonthFuelConsumptionByMonth(PageRequest.of(page,size));
        return result.getContent();
    }

    @Override
    public List<MonthFuelConsumptionDto> load(UUID driverId, int page, int size) {
        Page<MonthFuelConsumptionDto> result = fuelConsumptionRepository.calculateMonthFuelConsumptionByMonth(PageRequest.of(page,size));
        return result.getContent();
    }
}
