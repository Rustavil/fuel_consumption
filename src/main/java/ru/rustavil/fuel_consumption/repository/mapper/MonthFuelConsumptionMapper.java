package ru.rustavil.fuel_consumption.repository.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;
import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;

import java.util.List;

@Mapper
public interface MonthFuelConsumptionMapper {

    List<MonthFuelConsumption> from(List<MonthFuelConsumptionDto> load);

}
