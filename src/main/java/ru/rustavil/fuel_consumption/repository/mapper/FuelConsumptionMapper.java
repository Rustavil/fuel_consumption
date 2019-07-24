package ru.rustavil.fuel_consumption.repository.mapper;


import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;

import java.util.List;

@Mapper
public interface FuelConsumptionMapper {

    FuelConsumptionDto from(ru.rustavil.fuel_consumption.domain.FuelConsumption src);
    List<FuelConsumptionDto> from(List<ru.rustavil.fuel_consumption.domain.FuelConsumption> src);

    ru.rustavil.fuel_consumption.domain.FuelConsumption to(FuelConsumptionDto save);
    List<ru.rustavil.fuel_consumption.domain.FuelConsumption> to(List<FuelConsumptionDto> save);

}
