package ru.rustavil.fuel_consumption.domain;

import java.time.LocalDate;

public class MonthMoneyConsumption {

    private LocalDate date;
    private double value;

    public MonthMoneyConsumption() {
    }

    public MonthMoneyConsumption(LocalDate date, double value) {
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
