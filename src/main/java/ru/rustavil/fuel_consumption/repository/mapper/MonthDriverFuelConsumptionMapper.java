package ru.rustavil.fuel_consumption.repository.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.rustavil.fuel_consumption.domain.MonthDriverFuelConsumption;
import ru.rustavil.fuel_consumption.repository.entities.MonthDriverFuelConsumptionDto;

import java.util.List;

@Mapper
public interface MonthDriverFuelConsumptionMapper {

    List<MonthDriverFuelConsumption> to(Page<MonthDriverFuelConsumptionDto> calculateMonthDriverFuelConsumptionByMonth);
}
