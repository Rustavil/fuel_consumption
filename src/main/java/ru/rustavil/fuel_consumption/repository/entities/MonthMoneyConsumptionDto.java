package ru.rustavil.fuel_consumption.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
public class MonthMoneyConsumptionDto {

    private LocalDate date;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal money;

    public MonthMoneyConsumptionDto() {
    }

    public MonthMoneyConsumptionDto(int year, int month, BigDecimal money) {
        this.date = LocalDate.of(year, month, 1);
        this.money = money;
    }
}
