package ru.rustavil.fuel_consumption.repository.entities;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthMoneyConsumptionDto that = (MonthMoneyConsumptionDto) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, money);
    }
}
