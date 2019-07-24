package ru.rustavil.fuel_consumption.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;

import java.util.UUID;

public interface DriverRepositoryJpa extends JpaRepository<DriverDto, UUID> {

    DriverDto findByIdentifier(Long identifier);

}
