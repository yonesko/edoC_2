package main.symptomatology;

import main.PaymentDAO;
import main.model.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by gleb on 06.05.16.
 */
public class Three implements PaymentSymptom {
    private static final BigDecimal BOUND = new BigDecimal("2000");
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    @Override
    public boolean test(Payment arg) {
        List<Payment> l1 = paymentDAO.sameDay(arg);

        List<Payment> l2 = paymentDAO.byPayment(arg);

        l1.retainAll(l2);

        return paymentDAO.sum(l1).compareTo(BOUND) >= 0;
    }
}
