package ru.rustavil.fuel_consumption.repository.entities;

import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

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
    private Double fuelPrice;
    private LocalDate created;

    public FuelConsumptionDto() {
        this.id = UUID.randomUUID();
        this.created = LocalDate.now();
    }

    public FuelConsumptionDto(DriverDto driver, FuelType fuelType, Double fuelVolume, Double fuelPrice) {
        this.id = UUID.randomUUID();
        this.driver = driver;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
        this.created = LocalDate.now();
    }

    public FuelConsumptionDto(DriverDto driver, FuelType fuelType, Double fuelVolume, Double fuelPrice, LocalDate created) {
        this.id = UUID.randomUUID();
        this.driver = driver;
        this.fuelType = fuelType;
        this.fuelVolume = fuelVolume;
        this.fuelPrice = fuelPrice;
        this.created = created;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(Double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public Double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
