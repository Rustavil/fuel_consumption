package ru.rustavil.fuel_consumption.rest.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.rustavil.fuel_consumption.Application;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.domain.service.NotificationSender;
import ru.rustavil.fuel_consumption.repository.jpa.DriverRepositoryJpa;
import ru.rustavil.fuel_consumption.repository.jpa.FuelConsumptionRepositoryJpa;
import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;
import ru.rustavil.fuel_consumption.useCase.FuelPurchaseRegister;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FuelConsumptionEndpointIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DriverRepositoryJpa driverRepository;
    @Autowired
    private FuelConsumptionRepositoryJpa fuelConsumptionRepository;
    @Autowired
    private FuelPurchaseRegister register;
    @MockBean
    private NotificationSender notificationSender;

    @Before
    public void setUp() throws Exception {
        assertThat(mvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(register).isNotNull();
        assertThat(driverRepository).isNotNull();
        assertThat(fuelConsumptionRepository).isNotNull();
        assertThat(notificationSender).isNotNull();
    }

    @Test
    public void whenRegisteredValidFuelConsumptionThenExpected() throws Exception {
        doNothing().when(notificationSender).sendFuelPurchaseNotice(any());

        FuelConsumptionRequestDto requestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(requestDto);

        mvc.perform(
                post("/fuel_consumption").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRegisteredValidFuelConsumptionBulkFileThenExpected() throws Exception {
        doNothing().when(notificationSender).sendFuelPurchaseNotice(any());

        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(Collections.singletonList(fuelConsumptionRequestDto));
        MockMultipartFile file = new MockMultipartFile("file", "file.json", MediaType.APPLICATION_JSON.toString(), json.getBytes());

        mvc.perform(multipart("/fuel_consumption/bulk")
                .file(file))
                .andExpect(status().isOk());
    }
}
