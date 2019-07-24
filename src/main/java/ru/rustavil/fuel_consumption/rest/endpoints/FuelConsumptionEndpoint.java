package ru.rustavil.fuel_consumption.rest.endpoints;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.rustavil.fuel_consumption.domain.exceptions.ValidationException;
import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;
import ru.rustavil.fuel_consumption.rest.dto.FuelPurchaseRequestDto;
import ru.rustavil.fuel_consumption.useCase.FuelPurchaseRegister;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fuel_consumption")
public class FuelConsumptionEndpoint {

    private final FuelPurchaseRegister fuelConsumptionRegistrar;
    private final ObjectMapper mapper;
    private final Validator validator;

    @Autowired
    public FuelConsumptionEndpoint(FuelPurchaseRegister fuelConsumptionRegistrar, ObjectMapper mapper, Validator validator) {
        this.fuelConsumptionRegistrar = fuelConsumptionRegistrar;
        this.mapper = mapper;
        this.validator = validator;
    }

    @PostMapping
    public void registry(@Valid @RequestBody FuelConsumptionRequestDto fuelConsumptionRequestDto) {
        fuelConsumptionRegistrar.registerPurchase(new FuelPurchaseRequestDto(fuelConsumptionRequestDto));
    }

    @PostMapping("/bulk")
    public void registryBulk(@NotEmpty @RequestParam("file") MultipartFile file) throws IOException {
        List<FuelConsumptionRequestDto> fuelConsumptionRequestDtoList = mapper.readValue(file.getInputStream(), new TypeReference<List<FuelConsumptionRequestDto>>(){});
        for (FuelConsumptionRequestDto fuelConsumptionRequestDto : fuelConsumptionRequestDtoList) {
            Set<ConstraintViolation<FuelConsumptionRequestDto>> violations = validator.validate(fuelConsumptionRequestDto);
            if (!violations.isEmpty()) {
                throw new ValidationException("Fuel consumption bulk file invalid", violations);
            }
        }
        fuelConsumptionRegistrar.registerPurchase(new FuelPurchaseRequestDto(fuelConsumptionRequestDtoList));
    }
}
