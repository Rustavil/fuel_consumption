package ru.rustavil.fuel_consumption.repository.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;
import ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto;

import java.util.List;

@Mapper
public interface MonthMoneyConsumptionMapper {

    List<MonthMoneyConsumption> to(List<MonthMoneyConsumptionDto> dst);

}
