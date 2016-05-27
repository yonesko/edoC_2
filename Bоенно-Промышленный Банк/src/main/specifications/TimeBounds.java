package main.specifications;

import main.data.model.Payment;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;

/**
 * Compares inclusive.
 */
public class TimeBounds extends CompositeSpecification <Payment> {
    private LocalTime from;
    private LocalTime till;

    public TimeBounds(LocalTime from, LocalTime till) {
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        boolean leap;
        ISpecification spec;
        LocalTime midnight = LocalTime.MIDNIGHT.minusNanos(1);

        if (till.compareTo(from) >= 0) {
            spec = new AfterTime(from).and(new BeforeTime(till));
        }
        else//reverse order
            spec = new AfterTime(from).and(new BeforeTime(midnight))
                    .or(new AfterTime(midnight).and(new BeforeTime(till)));

        return spec.isSatisfiedBy(payment);
    }
}
