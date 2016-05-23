package main.symptomatology;

import main.PaymentDAO;
import main.model.Payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by gleb on 07.05.16.
 */
public class Seven implements PaymentSymptom {
    private static final BigDecimal SUM_BOUND = new BigDecimal("3000");
    private static final int COUNT_BOUND = 10;
    private static final int HOURS = 2;
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    @Override
    public boolean test(Payment arg) {
        Instant a, b;
        List<Payment> l;

        a = arg.getDate().minus(HOURS, ChronoUnit.HOURS);
        b = arg.getDate();

        l = paymentDAO.byPeriod(a, b);

        return l.size() >= COUNT_BOUND || paymentDAO.sum(l).compareTo(SUM_BOUND) >= 0;
    }
}
