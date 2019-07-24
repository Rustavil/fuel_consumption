package ru.rustavil.fuel_consumption.domain.exceptions;

public class EmptyFuelConsumptionException extends BaseException {

    public EmptyFuelConsumptionException() {
        super("Fuel consumption empty");
    }
}
