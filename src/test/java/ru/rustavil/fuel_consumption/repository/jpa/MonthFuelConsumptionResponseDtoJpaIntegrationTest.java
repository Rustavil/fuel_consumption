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
import ru.rustavil.fuel_consumption.repository.entities.MonthFuelConsumptionDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MonthFuelConsumptionResponseDtoJpaIntegrationTest {


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
    public void whenInvokeCalculateFuelConsumptionByMonthThenExpected() {
        Page<MonthFuelConsumptionDto>  found = fuelConsumptionRepositoryJpa.calculateMonthFuelConsumptionByMonth(PageRequest.of(0, 10));
        assertThat(found).contains(
                new MonthFuelConsumptionDto(2019, 6, FuelType.TYPE_98, 201.0, 4000.0, 2000.0),
                new MonthFuelConsumptionDto(2019, 6, FuelType.TYPE_95, 501.5, 8000.0, 2666.6666666666665),
                new MonthFuelConsumptionDto(2019, 7, FuelType.TYPE_D, 501.5, 8000.0, 2666.6666666666665)
        );
    }

    @Test
    public void whenInvokeCalculateFuelConsumptionByMonthAndDriverIdentifierThenExpected() {
        Page<MonthFuelConsumptionDto>  found = fuelConsumptionRepositoryJpa.calculateMonthFuelConsumptionByMonthAndDriverId(driverDtoList[1].getId(), PageRequest.of(0, 10));
        assertThat(found).contains(
                new MonthFuelConsumptionDto(2019, 6, FuelType.TYPE_98, 100.5, 2000.0, 2000.0),
                new MonthFuelConsumptionDto(2019, 6, FuelType.TYPE_95, 401.0, 6000.0, 3000.0),
                new MonthFuelConsumptionDto(2019, 7, FuelType.TYPE_D, 100.5, 2000.0, 2000.0)
        );
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
