package ru.rustavil.fuel_consumption.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.FuelConsumptionMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FuelConsumptionRepositoryImpl implements ru.rustavil.fuel_consumption.domain.repository.FuelConsumptionRepository {

    private final FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa;
    private final FuelConsumptionMapper mapper;

    @Override
    public FuelConsumption save(FuelConsumption fuelConsumption) {
        return mapper.to(fuelConsumptionRepositoryJpa.save(mapper.from(fuelConsumption)));
    }

    @Override
    public List<FuelConsumption> save(List<FuelConsumption> fuelConsumptionList) {
        return mapper.to(fuelConsumptionRepositoryJpa.saveAll(mapper.from(fuelConsumptionList)));
    }
}
