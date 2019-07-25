package ru.rustavil.fuel_consumption.repository.mapper;


import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;

import java.util.List;

@Mapper
public interface FuelConsumptionMapper {

    FuelConsumptionDto from(FuelConsumption src);
    List<FuelConsumptionDto> from(List<FuelConsumption> src);

    FuelConsumption to(FuelConsumptionDto save);
    List<FuelConsumption> to(List<FuelConsumptionDto> save);

}
