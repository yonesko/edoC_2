package main.specifications.payment;

import main.data.model.Payment;
import main.specifications.core.CompositeSpecification;
import main.specifications.core.ISpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.*;

/**
 * Compares inclusive.
 */
public class BetweenTime extends CompositeSpecification<Payment> {
    private static final Logger logger = LogManager.getLogger();
    private LocalTime from;
    private LocalTime till;

    public BetweenTime(LocalTime from, LocalTime till) {
        if (from == null || till == null)
            throw  new NullPointerException();
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        ISpecification spec;

        //if from > till then midnight is between from and till
        //thus payment date must be in [from; midnight] or in [midnight, till]
        if (from.compareTo(till) > 0)
            spec = new AfterTime(from).or(new BeforeTime(till));
        else
            spec = new AfterTime(from).and(new BeforeTime(till));

        logger.entry(payment);
        logger.entry(from);
        logger.entry(till);

        return logger.exit(spec.isSatisfiedBy(payment));
    }
}
