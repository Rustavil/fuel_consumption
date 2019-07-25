package ru.rustavil.fuel_consumption.rest.endpoints;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.rustavil.fuel_consumption.domain.FuelConsumption;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuel_consumption")
public class FuelConsumptionEndpoint {

    private final FuelPurchaseRegister fuelConsumptionRegistrar;
    private final ObjectMapper mapper;
    private final Validator validator;

    @PostMapping
    public ResponseEntity<List<String>> registry(@Valid @RequestBody FuelConsumptionRequestDto fuelConsumptionRequestDto) {
        return ResponseEntity.ok(
                fuelConsumptionRegistrar.registerPurchase(
                        new FuelPurchaseRequestDto(fuelConsumptionRequestDto)).
                        getFuelConsumptionList().stream().
                        map(FuelConsumption::getId).map(UUID::toString).
                        collect(Collectors.toList()));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<String>> registryBulk(@NotEmpty @RequestParam("file") MultipartFile file) throws IOException {
        List<FuelConsumptionRequestDto> fuelConsumptionRequestDtoList = mapper.readValue(file.getInputStream(), new TypeReference<List<FuelConsumptionRequestDto>>(){});
        for (FuelConsumptionRequestDto fuelConsumptionRequestDto : fuelConsumptionRequestDtoList) {
            Set<ConstraintViolation<FuelConsumptionRequestDto>> violations = validator.validate(fuelConsumptionRequestDto);
            if (!violations.isEmpty()) {
                throw new ValidationException("Fuel consumption bulk file invalid", violations);
            }
        }
        return ResponseEntity.ok(
                fuelConsumptionRegistrar.registerPurchase(
                        new FuelPurchaseRequestDto(fuelConsumptionRequestDtoList)).
                        getFuelConsumptionList().stream().
                        map(FuelConsumption::getId).map(UUID::toString).
                        collect(Collectors.toList()));
    }
}
