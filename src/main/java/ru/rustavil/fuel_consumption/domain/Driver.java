package ru.rustavil.fuel_consumption.domain;

import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.util.UUID;

public class Driver {

    private final UUID id;
    private final Long identifier;

    public Driver(Long identifier) {
        this.id = UUID.randomUUID();
        if (identifier <= 0) {
            throw new InvalidException("Driver identifier must be positive");
        }
        this.identifier = identifier;
    }

    public UUID getId() {
        return id;
    }

    public Long getIdentifier() {
        return identifier;
    }

}
