package ru.rustavil.fuel_consumption.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.FuelConsumptionMapper;

import java.util.List;

@Repository
public class FuelConsumptionRepositoryImpl implements ru.rustavil.fuel_consumption.domain.repository.FuelConsumptionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FuelConsumptionRepositoryImpl.class);
    private FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa;
    private FuelConsumptionMapper mapper;

    @Autowired
    public FuelConsumptionRepositoryImpl(FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa, FuelConsumptionMapper mapper) {
        this.fuelConsumptionRepositoryJpa = fuelConsumptionRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public FuelConsumption save(FuelConsumption fuelConsumption) {
        return mapper.to(fuelConsumptionRepositoryJpa.save(mapper.from(fuelConsumption)));
    }

    @Override
    public List<FuelConsumption> save(List<FuelConsumption> fuelConsumptionList) {
        return mapper.to(fuelConsumptionRepositoryJpa.saveAll(mapper.from(fuelConsumptionList)));
    }
}
