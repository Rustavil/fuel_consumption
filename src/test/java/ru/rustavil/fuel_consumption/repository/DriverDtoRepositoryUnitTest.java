package ru.rustavil.fuel_consumption.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.Driver;
import ru.rustavil.fuel_consumption.domain.repository.DriverRepository;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;
import ru.rustavil.fuel_consumption.repository.jpa.DriverRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.mapper.DriverMapper;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DriverDtoRepositoryUnitTest {


    @MockBean
    private DriverRepositoryJpa driverRepositoryJpa;
    @MockBean
    private DriverMapper driverMapper;
    @Autowired
    private DriverRepositoryImpl driverRepositoryImpl;

    @TestConfiguration
    static class DriverRepositoryTestContextConfiguration {

        @Bean
        public DriverRepository driverRepository(DriverRepositoryJpa driverRepositoryJpa, DriverMapper driverMapper) {
            return new DriverRepositoryImpl(driverRepositoryJpa, driverMapper);
        }
    }

    @Before
    public void setUp() throws Exception {
        assertThat(driverRepositoryJpa).isNotNull();
        assertThat(driverMapper).isNotNull();
        assertThat(driverRepositoryImpl).isNotNull();
    }

    @Test
    public void findByIdentifier() {
        Long driverIdentifier = 1L;
        when(driverRepositoryJpa.findByIdentifier(anyLong())).thenReturn(new DriverDto());
        when(driverMapper.to(any(DriverDto.class))).thenReturn(new Driver(driverIdentifier));

        Driver found = driverRepositoryImpl.findByIdentifier(driverIdentifier);
        assertThat(found.getIdentifier()).isEqualTo(driverIdentifier);
        verify(driverRepositoryJpa, atLeastOnce()).findByIdentifier(anyLong());
        verify(driverMapper, atLeastOnce()).to(any(DriverDto.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFoundByIdentifier() {
        when(driverRepositoryJpa.findByIdentifier(anyLong())).thenReturn(null);
        driverRepositoryImpl.findByIdentifier(anyLong());
    }

    @Test
    public void findById() {
        Driver expected = new Driver(111111L);
        when(driverRepositoryJpa.findById(any(UUID.class))).thenReturn(Optional.of(new DriverDto()));
        when(driverMapper.to(any(DriverDto.class))).thenReturn(expected);

        Driver found = driverRepositoryImpl.findById(UUID.randomUUID());
        assertThat(found.getId()).isEqualTo(expected.getId());
        verify(driverRepositoryJpa, atLeastOnce()).findById(any(UUID.class));
        verify(driverMapper, atLeastOnce()).to(any(DriverDto.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFoundById() {
        when(driverRepositoryJpa.findById(any(UUID.class))).thenReturn(null);
        when(driverMapper.to(any(DriverDto.class))).thenReturn(new Driver(111111L));

        driverRepositoryImpl.findById(any(UUID.class));
    }
}
