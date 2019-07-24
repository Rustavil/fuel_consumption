package ru.rustavil.fuel_consumption.useCase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.FuelPurchase;
import ru.rustavil.fuel_consumption.domain.FuelPurchaseNotification;
import ru.rustavil.fuel_consumption.domain.Notification;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.FuelConsumptionRepository;
import ru.rustavil.fuel_consumption.domain.service.NotificationSender;
import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;
import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;
import ru.rustavil.fuel_consumption.useCase.FuelPurchaseRegister;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class FuelPurchaseRegisterImpl implements FuelPurchaseRegister {

    private final DriverRepository driverRepository;
    private final FuelConsumptionRepository fuelConsumptionRepository;
    private final NotificationSender notificationSender;

    @Autowired
    public FuelPurchaseRegisterImpl(DriverRepository driverRepository, FuelConsumptionRepository fuelConsumptionRepository, NotificationSender notificationSender) {
        this.driverRepository = driverRepository;
        this.fuelConsumptionRepository = fuelConsumptionRepository;
        this.notificationSender = notificationSender;
    }

    @Transactional
    @Override
    public void registerPurchase(FuelPurchaseRequestDto fuelPurchaseRequestDto) {
        List<FuelConsumption> fuelConsumptionList = new LinkedList<>();
        for (FuelConsumptionRequestDto fuelConsumptionRequestDto : fuelPurchaseRequestDto.getFuelConsumptionRequestDtoList()) {
            Driver driver = driverRepository.findByIdentifier(fuelConsumptionRequestDto.getDriverIdentifier());
            fuelConsumptionList.add(new FuelConsumption(driver, fuelConsumptionRequestDto.getFuelType(), fuelConsumptionRequestDto.getFuelVolume(), fuelConsumptionRequestDto.getFuelPrice()));
        }
        FuelPurchase fuelPurchase = new FuelPurchase(fuelConsumptionList);
        fuelPurchase.IsCanRegister();
        fuelConsumptionRepository.save(fuelPurchase.getFuelConsumptionList());
        Notification notification = new FuelPurchaseNotification(fuelPurchase.getTotalFuelVolume(), fuelPurchase.getFuelVolumeMap(), fuelPurchase.getTotalPrice().doubleValue());
        notificationSender.sendFuelPurchaseNotice(notification);//todo use event listener after async method
    }
}
