package ru.rustavil.fuel_consumption.repository.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.entities.MonthMoneyConsumptionDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MonthMoneyConsumptionResponseDtoJpaIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private FuelConsumptionRepositoryJpa fuelConsumptionRepositoryJpa;

    private DriverDto[] driverDtoList;
    private FuelConsumptionDto[] fuelConsumptionDtoList;

    @Before
    public void setUp() throws Exception {
        assertThat(entityManager).isNotNull();
        assertThat(fuelConsumptionRepositoryJpa).isNotNull();

        saveDrivers();
        saveFuelConsumptions();
    }

    @Test
    public void whenInvokeCalculateMonthsMoneyConsumptionByDriverIdentifierThenExpected() {
        Page<MonthMoneyConsumptionDto> got = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumptionByDriverId(driverDtoList[0].getId(), PageRequest.of(0, 2));
        assertThat(got).contains(new MonthMoneyConsumptionDto(2019,6, 4000),
                new MonthMoneyConsumptionDto(2019,7, 6000));
    }

    @Test
    public void whenInvokeCalculateMonthsMoneyConsumptionByThenExpected() {
        Page<MonthMoneyConsumptionDto> got = fuelConsumptionRepositoryJpa.calculateMonthsMoneyConsumption(PageRequest.of(0, 2));
        assertThat(got).contains(new MonthMoneyConsumptionDto(2019,6, 12000),
                new MonthMoneyConsumptionDto(2019,7, 8000));
    }

    @After
    public void tearDown() throws Exception {
        removeFuelConsumptions();
        removeDrivers();
    }

    private void saveDrivers() {
        driverDtoList = new DriverDto[]{
                new DriverDto(111111L),
                new DriverDto(222222L)
        };
        for (DriverDto driverDto : driverDtoList) {
            entityManager.persistAndFlush(driverDto);
        }
    }

    private void removeDrivers() {
        for(DriverDto driverDto: driverDtoList){
            entityManager.remove(driverDto);
        }
        entityManager.flush();
    }

    private void saveFuelConsumptions() {
        fuelConsumptionDtoList = new FuelConsumptionDto[]{
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_95, 100.50, 2000.0, LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_98, 100.50, 2000.0, LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_D, 100.50, 2000.0, LocalDate.of(2019, 7, 1)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_D, 300.50, 4000.0, LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_95, 100.50, 2000.0, LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_98, 100.50, 2000.0, LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_D, 100.50, 2000.0, LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_95, 300.50, 4000.0, LocalDate.of(2019, 6, 1))};

        for (FuelConsumptionDto fuelConsumptionDto : fuelConsumptionDtoList) {
            entityManager.persistAndFlush(fuelConsumptionDto);
        }
    }

    private void removeFuelConsumptions() {
        for(FuelConsumptionDto fuelConsumptionDto: fuelConsumptionDtoList) {
            entityManager.remove(fuelConsumptionDto);
        }
        entityManager.flush();
    }
}
