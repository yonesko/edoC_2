package main.specifications.payment;

import main.data.model.Payment;
import main.specifications.core.CompositeSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Compares inclusive
 */
public class BeforeTime extends CompositeSpecification<Payment> {
    private LocalTime value;
    private static final Logger logger = LogManager.getLogger();

    public BeforeTime(LocalTime value) {
        if (value == null)
            throw new NullPointerException();
        this.value = value;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        OffsetDateTime offsetPaymentDate;
        offsetPaymentDate = OffsetDateTime.ofInstant(payment.getDate(), ZoneOffset.UTC);

        logger.entry(payment);
        logger.entry(offsetPaymentDate);

        return logger.exit(offsetPaymentDate.toLocalTime().compareTo(value) <= 0);
    }
}
