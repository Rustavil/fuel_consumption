package ru.rustavil.fuel_consumption.repository.entities;

import java.time.LocalDate;
import java.util.Objects;

public class MonthMoneyConsumptionDto {

    private LocalDate date;
    private double value;

    public MonthMoneyConsumptionDto() {
    }

    public MonthMoneyConsumptionDto(int year, int month, double value) {
        this.date = LocalDate.of(year, month, 1);
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthMoneyConsumptionDto that = (MonthMoneyConsumptionDto) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }
}
