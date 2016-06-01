package main.specifications.payment;

import main.data.model.Payment;
import main.specifications.core.CompositeSpecification;


public class SamePayment extends CompositeSpecification<Payment> {
    private Payment payment;

    public SamePayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        return payment.equals(payment);
    }
}
