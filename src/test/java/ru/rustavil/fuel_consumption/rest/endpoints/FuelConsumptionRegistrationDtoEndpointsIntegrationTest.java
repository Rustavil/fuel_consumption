package ru.rustavil.fuel_consumption.rest.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.rustavil.fuel_consumption.domain.FuelType;
import ru.rustavil.fuel_consumption.domain.exceptions.ResourceNotFoundException;
import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;
import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;
import ru.rustavil.fuel_consumption.useCase.FuelPurchaseRegister;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FuelConsumptionEndpoint.class)
public class FuelConsumptionRegistrationDtoEndpointsIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FuelPurchaseRegister register;

    @Before
    public void setUp() throws Exception {
        assertThat(mvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(register).isNotNull();
    }

    @Test
    public void whenRegisteredValidFuelConsumptionThenExpected() throws Exception {
        doNothing().when(register).registerPurchase(any(FuelPurchaseRequestDto.class));

        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(fuelConsumptionRequestDto);

        mvc.perform(
                post("/fuel_consumption").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRegisteredFuelConsumptionAndDriverNotFoundThenExpected() throws Exception {
        doThrow(new ResourceNotFoundException("Driver not found")).when(register).registerPurchase(any(FuelPurchaseRequestDto.class));

        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(fuelConsumptionRequestDto);

        mvc.perform(
                post("/fuel_consumption").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp", Matchers.startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
                .andExpect(jsonPath("$.error", Matchers.equalToIgnoringCase("driver not found")));
    }

    @Test
    public void whenRegisteredFuelConsumptionAndInvalidFuelVolumeThenExpected() throws Exception {
        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, -100.0, BigDecimal.valueOf(-200.0)
        );
        String json = objectMapper.writeValueAsString(fuelConsumptionRequestDto);

        mvc.perform(
                post("/fuel_consumption").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", Matchers.startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
                .andExpect(jsonPath("$.error", Matchers.equalToIgnoringCase("invalid argument")))
                .andExpect(jsonPath("$.fieldErrors", Matchers.hasKey("fuelVolume")))
                .andExpect(jsonPath("$.fieldErrors.fuelVolume", Matchers.equalToIgnoringCase("must be greater than 0")))
                .andExpect(jsonPath("$.fieldErrors", Matchers.hasKey("fuelPrice")))
                .andExpect(jsonPath("$.fieldErrors.fuelPrice", Matchers.equalToIgnoringCase("must be greater than 0")));
    }

    @Test
    public void whenRegisteredValidFuelConsumptionAndHappenedInternalErrorThenExpected() throws Exception {
        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(fuelConsumptionRequestDto);

        doThrow(new RuntimeException("internal error")).when(register).registerPurchase(any(FuelPurchaseRequestDto.class));

        mvc.perform(
                post("/fuel_consumption").
                        contentType(MediaType.APPLICATION_JSON).
                        content(json))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.timestamp", Matchers.startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
                .andExpect(jsonPath("$.error", Matchers.equalToIgnoringCase("internal error")));
    }

    @Test
    public void whenRegisteredValidFuelConsumptionBulkFileThenExpected() throws Exception {
        doNothing().when(register).registerPurchase(any(FuelPurchaseRequestDto.class));

        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, 100.0, BigDecimal.valueOf(200.0)
        );
        String json = objectMapper.writeValueAsString(Collections.singletonList(fuelConsumptionRequestDto));
        MockMultipartFile file = new MockMultipartFile("file", "file.json", MediaType.APPLICATION_JSON.toString(), json.getBytes());

        mvc.perform(multipart("/fuel_consumption/bulk")
                        .file(file))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRegisteredFuelConsumptionBulkFileAndFuelVolumeInvalidThenExpected() throws Exception {
        FuelConsumptionRequestDto fuelConsumptionRequestDto = new FuelConsumptionRequestDto(
                LocalDate.now(), 11111L, FuelType.TYPE_95, -100.0, BigDecimal.valueOf(-200.0)
        );
        String json = objectMapper.writeValueAsString(Collections.singletonList(fuelConsumptionRequestDto));
        MockMultipartFile file = new MockMultipartFile("file", "file.json", MediaType.APPLICATION_JSON.toString(), json.getBytes());

        mvc.perform(
                multipart("/fuel_consumption/bulk")
                        .file(file))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", Matchers.startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))))
                .andExpect(jsonPath("$.error", Matchers.equalToIgnoringCase("invalid argument")))
                .andExpect(jsonPath("$.fieldErrors", Matchers.hasKey("fuelVolume")))
                .andExpect(jsonPath("$.fieldErrors.fuelVolume", Matchers.equalToIgnoringCase("must be greater than 0")))
                .andExpect(jsonPath("$.fieldErrors", Matchers.hasKey("fuelPrice")))
                .andExpect(jsonPath("$.fieldErrors.fuelPrice", Matchers.equalToIgnoringCase("must be greater than 0")));
    }

}
