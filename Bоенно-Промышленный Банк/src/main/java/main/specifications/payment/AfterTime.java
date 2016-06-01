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
public class AfterTime extends CompositeSpecification<Payment> {
    private LocalTime value;
    private static final Logger logger = LogManager.getLogger();

    public AfterTime(LocalTime value) {
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
        logger.entry(value);

        return logger.exit(offsetPaymentDate.toLocalTime().compareTo(value) >= 0);
    }
}
