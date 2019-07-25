package ru.rustavil.fuel_consumption.useCase.impl;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FuelPurchaseRegisterImpl implements FuelPurchaseRegister {

    private final DriverRepository driverRepository;
    private final FuelConsumptionRepository fuelConsumptionRepository;
    private final NotificationSender notificationSender;

    @Transactional
    @Override
    public void registerPurchase(FuelPurchaseRequestDto fuelPurchaseRequestDto) {
        List<FuelConsumption> fuelConsumptionList = new LinkedList<>();
        for (FuelConsumptionRequestDto fuelConsumptionRequestDto : fuelPurchaseRequestDto.getFuelConsumptionRequestDtoList()) {
            Driver driver = driverRepository.findByIdentifier(fuelConsumptionRequestDto.getDriverIdentifier());
            fuelConsumptionList.add(FuelConsumption.builder().
                    driver(driver).
                    fuelType(fuelConsumptionRequestDto.getFuelType()).
                    fuelVolume(fuelConsumptionRequestDto.getFuelVolume()).
                    fuelPrice(fuelConsumptionRequestDto.getFuelPrice()).
                    build());
        }
        FuelPurchase fuelPurchase = new FuelPurchase(fuelConsumptionList);
        fuelConsumptionRepository.save(fuelPurchase.getFuelConsumptionList());
        Notification notification = new FuelPurchaseNotification(fuelPurchase.getTotalFuelVolume(), fuelPurchase.getFuelVolumeMap(), fuelPurchase.getTotalPrice().doubleValue());
        notificationSender.sendFuelPurchaseNotice(notification);//todo use event listener after async method
    }
}
