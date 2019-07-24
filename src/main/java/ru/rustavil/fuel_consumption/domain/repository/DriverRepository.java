package ru.rustavil.fuel_consumption.domain.repository;

import ru.rustavil.fuel_consumption.domain.Driver;

import java.util.UUID;

public interface DriverRepository {

    Driver findByIdentifier(Long identifier);

    Driver findById(UUID driverId);
}
