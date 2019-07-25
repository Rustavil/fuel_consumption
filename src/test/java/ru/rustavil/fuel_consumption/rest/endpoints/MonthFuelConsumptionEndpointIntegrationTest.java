package ru.rustavil.fuel_consumption.rest.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.rustavil.fuel_consumption.Application;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.repository.entities.DriverDto;
import ru.rustavil.fuel_consumption.repository.entities.FuelConsumptionDto;
import ru.rustavil.fuel_consumption.repository.jpa.DriverRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.useCase.MonthFuelConsumptionReport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MonthFuelConsumptionEndpointIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DriverRepositoryJpa driverRepository;
    @Autowired
    private FuelConsumptionRepositoryJpa fuelConsumptionRepository;
    @Autowired
    private MonthFuelConsumptionReport report;

    private List<DriverDto> driverDtoList;
    private List<FuelConsumptionDto> fuelConsumptionDtoList;

    @Before
    public void setUp() throws Exception {
        assertThat(mvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(report).isNotNull();
        assertThat(driverRepository).isNotNull();
        assertThat(fuelConsumptionRepository).isNotNull();

        saveDrivers();
        saveFuelConsumptions();
    }

    @Test
    public void whenLoadMonthFuelConsumptionThenExpected() throws Exception {
        mvc.perform(
                get("/fuel_consumption/month_fuel")).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$", hasSize(3))).
                andExpect(jsonPath("$.*.avgFuelPrice", hasItem(2666.6666666666665))).
                andExpect(jsonPath("$.*.totalFuelPrice", hasItem(8000.00))).
                andExpect(jsonPath("$.*.fuelVolume", hasItem(501.5))).
                andExpect(jsonPath("$.*.fuelType", hasItem("TYPE_95"))).
                andExpect(jsonPath("$.*.date", hasItem("2019-06-01")));
    }

    @Test
    public void whenLoadMonthFuelConsumptionForDriverThenExpected() throws Exception {
        mvc.perform(
                get("/fuel_consumption/month_fuel/"+driverDtoList.get(0).getId())).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$", hasSize(3))).
                andExpect(jsonPath("$.*.avgFuelPrice", hasItem(2000.0))).
                andExpect(jsonPath("$.*.avgFuelPrice", hasItem(2666.6666666666665))).
                andExpect(jsonPath("$.*.totalFuelPrice", hasItem(8000.00))).
                andExpect(jsonPath("$.*.totalFuelPrice", hasItem(4000.00))).
                andExpect(jsonPath("$.*.fuelVolume", hasItem(501.5))).
                andExpect(jsonPath("$.*.fuelVolume", hasItem(201.0))).
                andExpect(jsonPath("$.*.fuelType", hasItem("TYPE_95"))).
                andExpect(jsonPath("$.*.fuelType", hasItem("TYPE_98"))).
                andExpect(jsonPath("$.*.fuelType", hasItem("TYPE_D"))).
                andExpect(jsonPath("$.*.date", hasItem("2019-06-01"))).
                andExpect(jsonPath("$.*.date", hasItem("2019-07-01"))).
                andDo(r->System.out.println(r.getResponse().getContentAsString()));
    }

    @Test
    public void whenLoadMonthFuelConsumptionAndOneItemThenExpected() throws Exception {
        mvc.perform(
                get("/fuel_consumption/month_fuel").param("page", "0").param("size", "1")).
                andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$.*.date", hasItem("2019-06-01")));
    }

    @After
    public void tearDown() throws Exception {
        removeFuelConsumptions();
        removeDrivers();
    }

    private void saveDrivers() {
        driverDtoList = Arrays.asList(
                new DriverDto(111111L),
                new DriverDto(222222L)
        );
        driverRepository.saveAll(driverDtoList);
    }

    private void removeDrivers() {
        driverRepository.deleteAll(driverDtoList);
    }

    private void saveFuelConsumptions() {
        fuelConsumptionDtoList = Arrays.asList(
                new FuelConsumptionDto(driverDtoList.get(0), FuelType.TYPE_95, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList.get(0), FuelType.TYPE_98, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList.get(0), FuelType.TYPE_D, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 7, 1)),
                new FuelConsumptionDto(driverDtoList.get(0), FuelType.TYPE_D, 300.50, BigDecimal.valueOf(4000.0), LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList.get(1), FuelType.TYPE_95, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 1)),
                new FuelConsumptionDto(driverDtoList.get(1), FuelType.TYPE_98, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 6, 2)),
                new FuelConsumptionDto(driverDtoList.get(1), FuelType.TYPE_D, 100.50, BigDecimal.valueOf(2000.0), LocalDate.of(2019, 7, 2)),
                new FuelConsumptionDto(driverDtoList.get(1), FuelType.TYPE_95, 300.50, BigDecimal.valueOf(4000.0), LocalDate.of(2019, 6, 1)));

        fuelConsumptionRepository.saveAll(fuelConsumptionDtoList);
    }

    private void removeFuelConsumptions() {
        fuelConsumptionRepository.deleteAll(fuelConsumptionDtoList);
    }
}
