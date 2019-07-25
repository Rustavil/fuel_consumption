package ru.rustavil.fuel_consumption.rest.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthFuelConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthFuelConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthFuelConsumptionReport;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuel_consumption/month_fuel")
public class MonthFuelConsumptionEndpoint {

    private final MonthFuelConsumptionRestMapper mapper;
    private final MonthFuelConsumptionReport report;

    @GetMapping()
    public ResponseEntity<List<MonthFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(report.load(page, size)));
    }

    @GetMapping(value = "/{driver_id}")
    public ResponseEntity<List<MonthFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(
            @PathVariable("driver_id") UUID driverId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(report.load(driverId, page, size)));
    }
}
