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
import ru.rustavil.fuel_consumption.domain.repository.MonthMoneyConsumptionRepository;
import ru.rustavil.fuel_consumption.useCase.impl.MonthMoneyConsumptionReportImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MonthMoneyConsumptionReportUnitTest {

    @MockBean
    private DriverRepository driverRepository;
    @MockBean
    private MonthMoneyConsumptionRepository repository;
    @Autowired
    private MonthMoneyConsumptionReport report;

    @TestConfiguration
    static class MonthMoneyConsumptionRepositoryTestContextConfiguration {

        @Bean
        public MonthMoneyConsumptionReport report(
                DriverRepository driverRepository,
                MonthMoneyConsumptionRepository repository) {
            return new MonthMoneyConsumptionReportImpl(repository, driverRepository);
        }
    }

    @Before
    public void setUp() throws Exception {
        assertThat(driverRepository).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(report).isNotNull();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenMonthMoneyConsumptionReportAndDriverNotFoundThenExpected() {
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
