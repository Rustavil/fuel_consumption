package ru.rustavil.fuel_consumption.rest.dto;

import java.time.LocalDate;

public class MonthMoneyConsumptionResponseDto {

    private LocalDate date;
    private double value;

    public MonthMoneyConsumptionResponseDto() {
    }

    public MonthMoneyConsumptionResponseDto(LocalDate date, double value) {
        this.date = date;
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
}
