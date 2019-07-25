package ru.rustavil.fuel_consumption.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MonthMoneyConsumptionResponseDto {

    private LocalDate date;
    private BigDecimal money;

    public MonthMoneyConsumptionResponseDto() {
    }

    public MonthMoneyConsumptionResponseDto(LocalDate date, BigDecimal money) {
        this.date = date;
        this.money = money;
    }
}
