package main.symptomatology;

import main.PaymentDAO;
import main.model.Payment;

/**
 * Created by gleb on 07.05.16.
 */
public class Five implements PaymentSymptom {
    private static final PaymentDAO paymentDAO = PaymentDAO.getInstance();
    private static final int BOUND = 20;
    @Override
    public boolean test(Payment arg) {
        return paymentDAO.sameDay(arg).size() >= BOUND;
    }
}
