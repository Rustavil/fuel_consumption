package ru.rustavil.fuel_consumption.repository.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FuelConsumptionRepositoryJpaIntegrationTest {

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
    public void whenSaveNewValidFuelConsumptionThenExpected(){
        DriverDto expectedDriver = driverDtoList[0];
        FuelConsumptionDto expectedFuelConsumption = new FuelConsumptionDto(
                expectedDriver, FuelType.TYPE_95, 200.0, BigDecimal.valueOf(200000,2));

        fuelConsumptionRepositoryJpa.save(expectedFuelConsumption);

        FuelConsumptionDto found = fuelConsumptionRepositoryJpa.getOne(
                expectedFuelConsumption.getId());

        assertThat(found).isEqualTo(expectedFuelConsumption);

        entityManager.remove(found);
        entityManager.flush();
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
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_95, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_98, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_D, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 7, 1)),
                new FuelConsumptionDto(driverDtoList[0], FuelType.TYPE_D, 300.50, BigDecimal.valueOf(4000.0), LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_95, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_98, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_D, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList[1], FuelType.TYPE_95, 300.50, BigDecimal.valueOf(4000.0), LocalDate.of(2019, 6, 1))};

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
