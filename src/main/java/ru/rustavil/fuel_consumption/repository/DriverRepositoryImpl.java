package ru.rustavil.fuel_consumption.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.repository.jpa.DriverRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.DriverMapper;

import java.util.Objects;
import java.util.UUID;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    private static final Logger LOG = LoggerFactory.getLogger(DriverRepositoryImpl.class);

    private DriverRepositoryJpa driverRepositoryJpa;
    private DriverMapper mapper;

    @Autowired
    public DriverRepositoryImpl(DriverRepositoryJpa driverRepositoryJpa, DriverMapper mapper) {
        this.driverRepositoryJpa = driverRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public Driver findByIdentifier(Long identifier) {
        Driver driver = mapper.to(driverRepositoryJpa.findByIdentifier(identifier));
        if (Objects.isNull(driver)) {
            String msg = String.format("Driver with identifier %d not found", identifier);
            LOG.warn(msg);
            throw new ResourceNotFoundException(msg);
        }
        return driver;
    }

    @Override
    public Driver findById(UUID id) {
        return mapper.to(driverRepositoryJpa.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Driver with id %s not found", id))));
    }
}
