package main;

import main.model.Payment;
import main.symptomatology.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gleb on 06.05.16.
 */
public class BankSystem {
    private FraudFactory fraudFactory;

    public BankSystem() {
        fraudFactory = new FraudFactory();
    }

    public void proccess(Payment payment) {
        PaymentDAO.getInstance().add(payment);
        if (isSuspicious(payment)) {
            payment.moveTo(PaymentStatus.Tребует_подтверждения);
        } else {
            payment.moveTo(PaymentStatus.rотов_к_проведению);
        }
    }

    private boolean isSuspicious(Payment payment) {
        for (PaymentSymptom fraud : fraudFactory.getAllFrauds())
            if (fraud.test(payment))
                return true;
        return false;
    }
}
