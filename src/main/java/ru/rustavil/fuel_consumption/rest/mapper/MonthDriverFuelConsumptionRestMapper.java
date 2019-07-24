package ru.rustavil.fuel_consumption.rest.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.MonthDriverFuelConsumption;
import ru.rustavil.fuel_consumption.rest.dto.MonthDriverFuelConsumptionResponseDto;

import java.util.List;

@Mapper
public interface MonthDriverFuelConsumptionRestMapper {
    List<MonthDriverFuelConsumptionResponseDto> from(List<MonthDriverFuelConsumption> load);
}
