package ru.rustavil.fuel_consumption.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "fuel_consumption")
public class FuelConsumptionDto {

    @Id
    @Column(columnDefinition="uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverDto driver;
    private FuelType fuelType;
    private Double fuelVolume;
    private BigDecimal fuelPrice;
    private LocalDate created;

    public FuelConsumptionDto() {
        this.id = UUID.randomUUID();
        this.created = LocalDate.now();
    }

    public FuelConsumptionDto(DriverDto driver, FuelType fuelType, Double fuelVolume, BigDecimal fuelPrice) {
        this.id = UUID.randomUUID();
        this.driver = driver;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
        this.created = LocalDate.now();
    }

    public FuelConsumptionDto(DriverDto driver, FuelType fuelType, Double fuelVolume, BigDecimal fuelPrice, LocalDate created) {
        this.id = UUID.randomUUID();
        this.driver = driver;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
        this.created = created;
    }
}
