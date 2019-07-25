package ru.rustavil.fuel_consumption.rest.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthMoneyConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthMoneyConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthMoneyConsumptionReport;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuel_consumption/month_money")
public class MonthMoneyConsumptionEndpoint {

    private final MonthMoneyConsumptionRestMapper mapper;
    private final MonthMoneyConsumptionReport report;

    @GetMapping()
    public ResponseEntity<List<MonthMoneyConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(report.load(page, size)));
    }

    @GetMapping(value = "/{driver_id}")
    public ResponseEntity<List<MonthMoneyConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(
            @PathVariable("driver_id") UUID driverId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(report.load(driverId, page, size)));
    }
}
