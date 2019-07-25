package ru.rustavil.fuel_consumption.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.rustavil.fuel_consumption.domain.exceptions.InvalidException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class MonthMoneyConsumption {

    private final LocalDate date;
    private final BigDecimal money;

    public MonthMoneyConsumption(LocalDate date,
                                 BigDecimal money) {
        this.date = date;
        if (money.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidException("Money consumption must not be negative");
        }
        this.money = money;
    }

    public static MonthMoneyConsumptionBuilder builder() {
        return new ValidateMonthMoneyConsumptionBuilder();
    }

    private static class ValidateMonthMoneyConsumptionBuilder extends MonthMoneyConsumptionBuilder {

        @Override
        public MonthMoneyConsumption build() {
            if (super.money.compareTo(BigDecimal.ZERO) < 0){
                throw new InvalidException("Money consumption must not be negative");
            }
            return super.build();
        }

    }
}
