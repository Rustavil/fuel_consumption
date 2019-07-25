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
import ru.rustavil.fuel_consumption.domain.repository.MonthFuelConsumptionRepository;
import ru.rustavil.fuel_consumption.useCase.impl.MonthFuelConsumptionReportImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MonthFuelConsumptionReportUnitTest {

    @MockBean
    private DriverRepository driverRepository;
    @MockBean
    private MonthFuelConsumptionRepository repository;
    @Autowired
    private MonthFuelConsumptionReport report;

    @TestConfiguration
    static class MonthFuelConsumptionRepositoryTestContextConfiguration {

        @Bean
        public MonthFuelConsumptionReport report(
                DriverRepository driverRepository,
                MonthFuelConsumptionRepository repository) {
            return new MonthFuelConsumptionReportImpl(repository, driverRepository);
        }
    }

    @Before
    public void setUp() throws Exception {
        assertThat(driverRepository).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(report).isNotNull();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenMonthFuelConsumptionReportAndDriverNotFoundThenExpected() {
        UUID driverId = UUID.randomUUID();

        when(driverRepository.findById(eq(driverId))).thenThrow(ResourceNotFoundException.class);

        try {
            report.load(driverId, 10, 0);
        } finally {
            verify(driverRepository, Mockito.atLeast(1)).findById(eq(driverId));
            verify(repository, Mockito.never()).load(any(UUID.class), anyInt(), anyInt());
        }
    }
}
