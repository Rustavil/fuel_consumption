package ru.rustavil.fuel_consumption.rest.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthDriverFuelConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthDriverFuelConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthDriverFuelConsumptionReport;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuel_consumption/month_driver_fuel")
public class MonthDriverFuelConsumptionEndpoint {

    private final MonthDriverFuelConsumptionRestMapper mapper;
    private final MonthDriverFuelConsumptionReport report;

    @GetMapping(value = "/stest")
    public ResponseEntity<String> stest() {
        return ResponseEntity.ok("123");
    }
    @GetMapping(value = "/{yyyy_MM}")
    public ResponseEntity<List<MonthDriverFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(
            @PathVariable("yyyy_MM") @DateTimeFormat(pattern = "yyyy_MM") Date month,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(mapper.from(report.load(month.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), page, size)));
    }

    @GetMapping(value = "/{yyyy_MM}/{driver_id}")
    public ResponseEntity<List<MonthDriverFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(
            @PathVariable("yyyy_MM") @DateTimeFormat(pattern = "yyyy_MM") Date month,
            @PathVariable("driver_id") UUID driverId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(mapper.from(report.load(month.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), driverId, page, size)));
    }
}
