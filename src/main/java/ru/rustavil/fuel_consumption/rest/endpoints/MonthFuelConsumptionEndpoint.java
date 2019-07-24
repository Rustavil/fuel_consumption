package ru.rustavil.fuel_consumption.rest.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthFuelConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthFuelConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthFuelConsumptionReport;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fuel_consumption/month_fuel")
public class MonthFuelConsumptionEndpoint {

    private final MonthFuelConsumptionRestMapper mapper;
    private final MonthFuelConsumptionReport monthFuelConsumptionReport;

    @Autowired
    public MonthFuelConsumptionEndpoint(MonthFuelConsumptionRestMapper mapper, MonthFuelConsumptionReport monthFuelConsumptionReport) {
        this.mapper = mapper;
        this.monthFuelConsumptionReport = monthFuelConsumptionReport;
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<List<MonthFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(@RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthFuelConsumptionReport.load(page,size)));
    }

    @GetMapping(value = "/{id}", params = { "page", "size" })
    public ResponseEntity<List<MonthFuelConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(@PathVariable("id") UUID driverId,
                                                                                                                @RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthFuelConsumptionReport.loadByDriverId(driverId, page, size)));
    }
}
