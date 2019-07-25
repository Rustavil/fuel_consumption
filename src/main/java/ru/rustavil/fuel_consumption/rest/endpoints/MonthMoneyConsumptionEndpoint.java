package ru.rustavil.fuel_consumption.rest.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustavil.fuel_consumption.rest.dto.MonthMoneyConsumptionResponseDto;
import ru.rustavil.fuel_consumption.rest.mapper.MonthMoneyConsumptionRestMapper;
import ru.rustavil.fuel_consumption.useCase.MonthMoneyConsumptionReport;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fuel_consumption/month_money")
public class MonthMoneyConsumptionEndpoint {

    private final MonthMoneyConsumptionRestMapper mapper;
    private final MonthMoneyConsumptionReport monthMoneyConsumptionReport;

    @Autowired
    public MonthMoneyConsumptionEndpoint(MonthMoneyConsumptionRestMapper mapper, MonthMoneyConsumptionReport monthMoneyConsumptionReport) {
        this.mapper = mapper;
        this.monthMoneyConsumptionReport = monthMoneyConsumptionReport;
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity<List<MonthMoneyConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntity(@RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthMoneyConsumptionReport.load(page,size)));
    }

    @GetMapping(value = "/{id}", params = { "page", "size" })
    public ResponseEntity<List<MonthMoneyConsumptionResponseDto>> monthMoneyConsumptionDtoResponseEntityByDriver(@PathVariable("id") UUID driverId,
                                                                                                                 @RequestParam(value = "page", defaultValue = "10") int page,
                                                                                                                 @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(mapper.from(monthMoneyConsumptionReport.load(driverId, page, size)));
    }
}
