package ru.rustavil.fuel_consumption.domain.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.repository.FuelConsumptionRepositoryImpl;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.FuelConsumptionMapper;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FuelConsumptionRequestDtoRepositoryUnitTest {

    @MockBean
    private FuelConsumptionRepositoryJpa jpa;
    @MockBean
    private FuelConsumptionMapper mapper;
    @Autowired
    private FuelConsumptionRepository repository;

    @TestConfiguration
    static class FuelConsumptionDtoRepositoryTestContextConfiguration {

        @Bean
        public FuelConsumptionRepository fuelConsumptionRepository(FuelConsumptionRepositoryJpa repositoryJpa, FuelConsumptionMapper mapper) {
            return new FuelConsumptionRepositoryImpl(repositoryJpa, mapper);
        }
    }

    @Before
    public void setUp() throws Exception {
        assertThat(jpa).isNotNull();
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void save(){
        Driver expectedDriver = new Driver(111111L);
        FuelConsumption expectedFuelConsumption = new FuelConsumption(expectedDriver, FuelType.TYPE_95, 200.0, BigDecimal.valueOf(2000.0));

        when(mapper.from(eq(expectedFuelConsumption))).thenReturn(new FuelConsumptionDto());
        when(jpa.save(any(FuelConsumptionDto.class))).thenReturn(new FuelConsumptionDto());
        when(mapper.to(any(FuelConsumptionDto.class))).thenReturn(any(FuelConsumption.class));

        repository.save(expectedFuelConsumption);

        verify(mapper, atLeastOnce()).from(any(FuelConsumption.class));
        verify(mapper, atLeastOnce()).to(any(FuelConsumptionDto.class));
        verify(jpa, atLeastOnce()).save(any(FuelConsumptionDto.class));
    }

    @Test
    public void saveAll() {
        when(mapper.from(eq(Collections.singletonList(new FuelConsumption())))).thenReturn(Collections.singletonList(new FuelConsumptionDto()));
        when(jpa.saveAll(eq(Collections.singletonList(new FuelConsumptionDto())))).thenReturn(Collections.singletonList(new FuelConsumptionDto()));
        when(mapper.to(eq(Collections.singletonList(new FuelConsumptionDto())))).thenReturn(anyListOf(FuelConsumption.class));

        repository.save(Collections.singletonList(new FuelConsumption()));

        verify(mapper, atLeastOnce()).from(anyListOf(FuelConsumption.class));
        verify(mapper, atLeastOnce()).to(anyListOf(FuelConsumptionDto.class));
        verify(jpa, atLeastOnce()).saveAll(anyListOf(FuelConsumptionDto.class));
    }
}
