package ru.rustavil.fuel_consumption.domain;

import java.util.UUID;

public class Driver {

    private UUID id;
    private Long identifier;

    public Driver() {
        this.id = UUID.randomUUID();
    }

    public Driver(UUID id) {
        this.id = id;
    }

    public Driver(Long identifier) {
        this.id = UUID.randomUUID();
        this.identifier = identifier;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
}
