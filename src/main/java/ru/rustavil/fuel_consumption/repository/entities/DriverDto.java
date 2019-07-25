package ru.rustavil.fuel_consumption.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode
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
}
