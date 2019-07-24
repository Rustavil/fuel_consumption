package ru.rustavil.fuel_consumption.domain;

import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthMoneyConsumption {

    private final LocalDate date;
    private final BigDecimal money;

    public MonthMoneyConsumption(LocalDate date,
                                 BigDecimal money) {
        this.date = date;
        if (money.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidException("Money consumption must not be negative");
        }
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getMoney() {
        return money;
    }
}
