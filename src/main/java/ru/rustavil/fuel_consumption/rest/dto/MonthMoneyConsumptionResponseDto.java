package ru.rustavil.fuel_consumption.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthMoneyConsumptionResponseDto {

    private LocalDate date;
    private BigDecimal money;

    public MonthMoneyConsumptionResponseDto() {
    }

    public MonthMoneyConsumptionResponseDto(LocalDate date, BigDecimal money) {
        this.date = date;
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
