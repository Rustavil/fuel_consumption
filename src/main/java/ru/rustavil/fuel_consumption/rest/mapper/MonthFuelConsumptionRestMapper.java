package ru.rustavil.fuel_consumption.rest.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.MonthFuelConsumption;
import ru.rustavil.fuel_consumption.rest.dto.MonthFuelConsumptionResponseDto;

import java.util.List;

@Mapper
public interface MonthFuelConsumptionRestMapper {

    List<MonthFuelConsumptionResponseDto> from(List<MonthFuelConsumption> load);
}
