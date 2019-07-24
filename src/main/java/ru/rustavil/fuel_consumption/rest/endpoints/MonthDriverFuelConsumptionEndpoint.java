package ru.rustavil.fuel_consumption.rest.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthDriverFuelConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthDriverFuelConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthDriverFuelConsumptionReport;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fuel_consumption/month_driver_fuel")
public class MonthDriverFuelConsumptionEndpoint {

    private final MonthDriverFuelConsumptionRestMapper mapper;
    private final MonthDriverFuelConsumptionReport monthDriverFuelConsumptionReport;

    @Autowired
    public MonthDriverFuelConsumptionEndpoint(MonthDriverFuelConsumptionRestMapper mapper, MonthDriverFuelConsumptionReport MonthDriverFuelConsumptionReport) {
        this.mapper = mapper;
        this.monthDriverFuelConsumptionReport = MonthDriverFuelConsumptionReport;
    }

    @GetMapping(value = "/{month}", params = { "page", "size" })
    public ResponseEntity<List<MonthDriverFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(@PathVariable("month") @DateTimeFormat(pattern = "yyyy_MM") LocalDate month,
                                                                                                              @RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthDriverFuelConsumptionReport.load(month, page,size)));
    }

    @GetMapping(value = "/{month}/{id}", params = { "page", "size" })
    public ResponseEntity<List<MonthDriverFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(@PathVariable("month") @DateTimeFormat(pattern = "yyyy_MM") LocalDate month,
                                                                                                                      @PathVariable("id") UUID driverId,
                                                                                                                      @RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthDriverFuelConsumptionReport.load(month, driverId, page, size)));
    }
}
