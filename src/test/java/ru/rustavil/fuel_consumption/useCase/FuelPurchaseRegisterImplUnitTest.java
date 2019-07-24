package ru.rustavil.fuel_consumption.useCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.domain.FuelPurchaseNotification;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.FuelConsumptionRepository;
import ru.rustavil.fuel_consumption.domain.service.NotificationSender;
import ru.rustavil.fuel_consumption.domain.exceptions.BaseException;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;
import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;
import ru.rustavil.fuel_consumption.useCase.impl.FuelPurchaseRegisterImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FuelPurchaseRegisterImplUnitTest {

    @MockBean
    private DriverRepository driverRepository;
    @MockBean
    private FuelConsumptionRepository fuelConsumptionRepository;
    @MockBean
    private NotificationSender notificationSender;
    @Autowired
    private FuelPurchaseRegister fuelPurchaseRegister;

    @TestConfiguration
    static class FuelPurchaseRegisterTestContextConfiguration {

        @Bean
        public FuelPurchaseRegister fuelPurchaseRegister(
                DriverRepository driverRepository,
                FuelConsumptionRepository fuelConsumptionRepository,
                NotificationSender notificationSender) {
            return new FuelPurchaseRegisterImpl(driverRepository, fuelConsumptionRepository, notificationSender);
        }
    }

    @Before
    public void setUp() {
        assertThat(driverRepository).isNotNull();
        assertThat(fuelConsumptionRepository).isNotNull();
        assertThat(notificationSender).isNotNull();
        assertThat(fuelPurchaseRegister).isNotNull();
    }

    @Test
    public void registrationSuccess() {
        Long driverIdentifier = 11111L;

        when(driverRepository.findByIdentifier(eq(driverIdentifier))).thenReturn(new Driver(driverIdentifier));

        fuelPurchaseRegister.registerPurchase(new FuelPurchaseRequestDto(Arrays.asList(
                new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 10.0, BigDecimal.valueOf(10.0)),
                new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 20.0, BigDecimal.valueOf(10.0))
        )));

        verify(driverRepository, Mockito.atLeast(1)).findByIdentifier(eq(driverIdentifier));
        verify(fuelConsumptionRepository, Mockito.atLeastOnce()).save(anyListOf(FuelConsumption.class));
        verify(notificationSender, Mockito.atLeastOnce()).sendFuelPurchaseNotice(any(FuelPurchaseNotification.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void registrationFailWhenDriverNotFound() {
        Long driverIdentifier = 11111L;

        when(driverRepository.findByIdentifier(eq(driverIdentifier))).thenThrow(ResourceNotFoundException.class);

        try {
            fuelPurchaseRegister.registerPurchase(new FuelPurchaseRequestDto(Arrays.asList(
                    new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 10.0, BigDecimal.valueOf(10.0)),
                    new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 20.0, BigDecimal.valueOf(10.0))
            )));
        } finally {
            verify(driverRepository, Mockito.atLeast(1)).findByIdentifier(eq(driverIdentifier));
            verify(fuelConsumptionRepository, Mockito.never()).save(anyListOf(FuelConsumption.class));
            verify(notificationSender, Mockito.never()).sendFuelPurchaseNotice(any(FuelPurchaseNotification.class));
        }
    }

    @Test(expected = BaseException.class)
    public void registrationFailWhenFuelConsumptionNotSave() {
        Long driverIdentifier = 11111L;

        when(driverRepository.findByIdentifier(eq(driverIdentifier))).thenReturn(new Driver(11111L));
        when(fuelConsumptionRepository.save(anyListOf(FuelConsumption.class))).thenThrow(BaseException.class);

        try {
            fuelPurchaseRegister.registerPurchase(new FuelPurchaseRequestDto(Arrays.asList(
                    new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 10.0, BigDecimal.valueOf(10.0)),
                    new FuelConsumptionRequestDto(LocalDate.now(), driverIdentifier, FuelType.TYPE_95, 20.0, BigDecimal.valueOf(10.0))
            )));
        } finally {
            verify(driverRepository, Mockito.atLeast(1)).findByIdentifier(eq(driverIdentifier));
            verify(fuelConsumptionRepository, Mockito.atLeastOnce()).save(anyListOf(FuelConsumption.class));
            verify(notificationSender, Mockito.never()).sendFuelPurchaseNotice(any(FuelPurchaseNotification.class));
        }
    }
}
