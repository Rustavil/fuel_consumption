package ru.rustavil.fuel_consumption.domain.service;

import ru.rustavil.fuel_consumption.domain.Notification;

public interface NotificationSender {

    void sendFuelPurchaseNotice(Notification notification);

}
