package ru.rustavil.fuel_consumption.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.entities.MonthDriverFuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto;

import java.time.LocalDate;
import java.util.UUID;

public interface FuelConsumptionRepositoryJpa extends JpaRepository<FuelConsumptionDto, UUID> {

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto(year(fc.created), month(fc.created), sum(fc.fuelPrice)) from FuelConsumptionDto fc group by year(fc.created), month(fc.created) order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthMoneyConsumptionDto> calculateMonthsMoneyConsumption(Pageable pageable);

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto(year(fc.created), month(fc.created), sum(fc.fuelPrice)) from FuelConsumptionDto fc where fc.driver.id = ?1 group by year(fc.created), month(fc.created) order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthMoneyConsumptionDto> calculateMonthsMoneyConsumptionByDriverId(UUID driverId, Pageable pageable);

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthDriverFuelConsumptionDto(year(fc.created), month(fc.created), fc.driver.identifier, fc.fuelType, sum(fc.fuelVolume), sum(fc.fuelPrice)) from FuelConsumptionDto fc where year(fc.created) = year(?1) and month(fc.created) = month(?1) group by year(fc.created), month(fc.created), fc.driver.identifier, fc.fuelType order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthDriverFuelConsumptionDto> calculateMonthDriverFuelConsumptionByMonth(LocalDate date, Pageable pageable);

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthDriverFuelConsumptionDto(year(fc.created), month(fc.created), fc.driver.identifier, fc.fuelType, sum(fc.fuelVolume), sum(fc.fuelPrice)) from FuelConsumptionDto fc where year(fc.created) = year(?1) and month(fc.created) = month(?1) and fc.driver.id = ?2 group by year(fc.created), month(fc.created), fc.driver.identifier, fc.fuelType order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthDriverFuelConsumptionDto> calculateMonthDriverFuelConsumptionByMonthAndDriverId(LocalDate date, UUID driverId, Pageable pageable);

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto(year(fc.created), month(fc.created), fc.fuelType, sum(fc.fuelVolume), sum(fc.fuelPrice), avg(fc.fuelPrice)) from FuelConsumptionDto fc group by year(fc.created), month(fc.created), fc.fuelType order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthFuelConsumptionDto> calculateMonthFuelConsumptionByMonth(Pageable pageable);

    @Query("select new ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto(year(fc.created), month(fc.created), fc.fuelType, sum(fc.fuelVolume), sum(fc.fuelPrice), avg(fc.fuelPrice)) from FuelConsumptionDto fc where fc.driver.id = ?1 group by year(fc.created), month(fc.created), fc.fuelType order by year(fc.created) asc, month(fc.created) asc")
    Page<MonthFuelConsumptionDto> calculateMonthFuelConsumptionByMonthAndDriverId(UUID driverId, Pageable pageable);
}
