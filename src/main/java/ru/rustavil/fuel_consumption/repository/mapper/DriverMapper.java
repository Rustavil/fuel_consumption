package ru.rustavil.fuel_consumption.repository.mapper;

import org.mapstruct.Mapper;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;

@Mapper
public interface DriverMapper {

    DriverDto from(Driver src);
    Driver to(DriverDto dst);

}
