package main.symptomatology;

import main.PaymentDAO;
import main.model.Payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by gleb on 07.05.16.
 */
public class Six implements PaymentSymptom {
    private static final LocalTime FROM = LocalTime.of(9, 0);
    private static final LocalTime TILL = LocalTime.of(23, 0);
    private static final BigDecimal SUM_BOUND = new BigDecimal("4000");
    private static final int COUNT_BOUND = 30;
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    @Override
    public boolean test(Payment arg) {
        List<Payment> l1;
        Instant a, b;

        a = arg.getDate().truncatedTo(ChronoUnit.DAYS).plus(FROM.getNano(), ChronoUnit.NANOS);
        b = arg.getDate().truncatedTo(ChronoUnit.DAYS).plus(TILL.getNano(), ChronoUnit.NANOS);

        l1 = paymentDAO.byProduct(arg.getProduct());
        l1.retainAll(paymentDAO.byPeriod(a, b));

        return l1.size() > COUNT_BOUND || paymentDAO.sum(l1).compareTo(SUM_BOUND) >= 0;
    }
}
