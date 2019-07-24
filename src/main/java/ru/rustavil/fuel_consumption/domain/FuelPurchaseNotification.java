package ru.rustavil.fuel_consumption.domain;

import com.google.common.base.Joiner;

import java.util.Map;

public class FuelPurchaseNotification implements Notification {

    private static final String TITLE = "New fuel purchase!";
    private static final String MSG_TMPL = "Total fuel volume: %.2fL (%s). Total price: %.2fEUR";

    private Double totalFuelVolume;
    private Map<FuelType, Double> fuelVolumeMap;
    private Double totalPrice;

    public FuelPurchaseNotification(Double totalFuelVolume, Map<FuelType, Double> fuelVolumeMap, Double totalPrice) {
        this.totalFuelVolume = totalFuelVolume;
        this.fuelVolumeMap = fuelVolumeMap;
        this.totalPrice = totalPrice;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String buildMsg() {
        return String.format(MSG_TMPL, totalFuelVolume, Joiner.on(",").withKeyValueSeparator(":").join(this.fuelVolumeMap), totalPrice);
    }

}
