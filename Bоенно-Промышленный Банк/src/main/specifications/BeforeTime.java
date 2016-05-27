package main.specifications;

import main.data.model.Payment;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class BeforeTime extends CompositeSpecification <Payment> {
    private LocalTime value;

    public BeforeTime(LocalTime value) {
        this.value = value;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        Instant adjusted = payment.getDate().truncatedTo(ChronoUnit.DAYS).plusNanos(value.toNanoOfDay());
        return payment.getDate().compareTo(adjusted) <= 0;
    }
}
