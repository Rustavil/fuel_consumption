package ru.rustavil.fuel_consumption.useCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.repository.MonthDriverFuelRepository;
import ru.rustavil.fuel_consumption.useCase.impl.MonthDriverFuelConsumptionReportImpl;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MonthDriverFuelConsumptionReportUnitTest {

    @MockBean
    private DriverRepository driverRepository;
    @MockBean
    private MonthDriverFuelRepository repository;
    @Autowired
    private MonthDriverFuelConsumptionReport monthDriverFuelConsumptionReport;

    @TestConfiguration
    static class MonthDriverFuelConsumptionReportTestContextConfiguration {

        @Bean
        public MonthDriverFuelConsumptionReport monthDriverFuelConsumptionReport(
                DriverRepository driverRepository,
                MonthDriverFuelRepository repository) {
            return new MonthDriverFuelConsumptionReportImpl(repository, driverRepository);
        }
    }

    @Before
    public void setUp() throws Exception {
        assertThat(driverRepository).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(monthDriverFuelConsumptionReport).isNotNull();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenMonthDriverFuelConsumptionReportAndDriverNotFoundThenExpected() {
        UUID driverId = UUID.randomUUID();

        when(driverRepository.findById(eq(driverId))).thenThrow(ResourceNotFoundException.class);

        try {
            monthDriverFuelConsumptionReport.load(LocalDate.of(2019, 7, 1), driverId, 10, 0);
        } finally {
            verify(driverRepository, Mockito.atLeast(1)).findById(eq(driverId));
            verify(repository, Mockito.never()).load(any(LocalDate.class), any(UUID.class), anyInt(), anyInt());
        }
    }
}
