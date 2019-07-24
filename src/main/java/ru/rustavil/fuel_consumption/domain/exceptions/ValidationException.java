package ru.rustavil.fuel_consumption.domain.exceptions;

import ru.rustavil.fuel_consumption.rest.dto.FuelConsumptionRequestDto;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends BaseException {

    private Set<ConstraintViolation<FuelConsumptionRequestDto>> violations;

    public ValidationException(String message, Set<ConstraintViolation<FuelConsumptionRequestDto>> violations) {
        super(message);
        this.violations = violations;
    }

    public ValidationException(String message) {
        super(message);
    }

    public Set<ConstraintViolation<FuelConsumptionRequestDto>> getViolations() {
        return violations;
    }

    public void setViolations(Set<ConstraintViolation<FuelConsumptionRequestDto>> violations) {
        this.violations = violations;
    }
}
