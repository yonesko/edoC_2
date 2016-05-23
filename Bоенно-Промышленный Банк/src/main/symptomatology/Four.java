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
public class Four implements PaymentSymptom {
    private static final BigDecimal BOUND = new BigDecimal("3000");
    private static final int HOURS = 1;
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    @Override
    public boolean test(Payment arg) {
        Instant a, b;
        List<Payment> l1, l2;

        a = arg.getDate().minus(HOURS, ChronoUnit.HOURS);
        b = arg.getDate();
        l1 = paymentDAO.byProduct(arg.getProduct());
        l2 = paymentDAO.byPeriod(a, b);

        l1.retainAll(l2);

        return paymentDAO.sum(l1).compareTo(BOUND) >= 0;
    }
}
