package ru.rustavil.fuel_consumption.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "drivers")
public class DriverDto {

    @Id
    @Column(columnDefinition="uuid")
    private UUID id;
    @Column(unique = true)
    private Long identifier;

    public DriverDto() {
        this.id = UUID.randomUUID();
    }

    public DriverDto(UUID id) {
        this.id = id;
    }

    public DriverDto(Long identifier) {
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
