package ru.rustavil.fuel_consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class Driver {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final Long identifier;

    public static DriverBuilder builder() {
        return new ValidateDriverBuilder();
    }

    private static class ValidateDriverBuilder extends DriverBuilder {

        @Override
        public Driver build() {
            if (super.identifier <= 0) {
                throw new InvalidException("Driver identifier must be positive");
            }
            return super.build();
        }

    }
}
