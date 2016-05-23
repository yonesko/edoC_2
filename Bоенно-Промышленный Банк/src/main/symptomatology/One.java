package main.symptomatology;

import main.PaymentDAO;
import main.model.Payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by gleb on 06.05.16.
 */
public class One implements PaymentSymptom {
    private static final BigDecimal BOUND = new BigDecimal("5000");
    private static final byte FROM = 9;
    private static final byte TILL = 23;
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    @Override
    public boolean test(Payment arg) {
        Instant a, b;

        a = arg.getDate().truncatedTo(ChronoUnit.DAYS).plus(FROM, ChronoUnit.HOURS);
        b = arg.getDate().truncatedTo(ChronoUnit.DAYS).plus(TILL, ChronoUnit.HOURS);

        List<Payment> l = paymentDAO.byPeriod(a, b);
        l.removeAll(paymentDAO.byProduct(arg.getProduct()));

        return paymentDAO.sum(l).compareTo(BOUND) >= 0;
    }
}
