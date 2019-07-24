package ru.rustavil.fuel_consumption.repository.entities;

import ru.rustavil.fuel_consumption.domain.FuelType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
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

    public BigDecimal getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(BigDecimal fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelConsumptionDto that = (FuelConsumptionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(driver, that.driver) &&
                fuelType == that.fuelType &&
                Objects.equals(fuelVolume, that.fuelVolume) &&
                Objects.equals(fuelPrice, that.fuelPrice) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driver, fuelType, fuelVolume, fuelPrice, created);
    }
}
