package ru.rustavil.fuel_consumption.rest.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.MonthMoneyConsumption;
import ru.rustavil.fuel_consumption.rest.dto.MonthMoneyConsumptionResponseDto;

import java.util.List;

@Mapper
public interface MonthMoneyConsumptionRestMapper {

    List<MonthMoneyConsumptionResponseDto> from(List<MonthMoneyConsumption> src);

}
