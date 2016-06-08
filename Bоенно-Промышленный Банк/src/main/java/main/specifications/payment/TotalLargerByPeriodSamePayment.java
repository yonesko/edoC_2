package main.specifications.payment;

import main.data.PaymentDAO;
import main.data.model.Payment;
import main.specifications.core.CompositeSpecification;
import main.specifications.core.ISpecification;

import java.math.BigDecimal;
import java.time.LocalTime;

public class TotalLargerByPeriodSamePayment extends CompositeSpecification<Payment> {
    private BigDecimal bound;
    private LocalTime from;
    private LocalTime till;
    private PaymentDAO paymentDAO = PaymentDAO.getInstance();

    public TotalLargerByPeriodSamePayment(BigDecimal bound, LocalTime from, LocalTime till) {
        this.bound = bound;
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isSatisfiedBy(Payment arg) {
        ISpecification spec;

        spec = new BetweenTime(from, till)
                .and(new SamePayment(arg));

        return paymentDAO.sum(paymentDAO.filter(spec)).compareTo(bound) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalLargerByPeriodSamePayment that = (TotalLargerByPeriodSamePayment) o;

        if (!bound.equals(that.bound)) return false;
        if (!from.equals(that.from)) return false;
        return till.equals(that.till);

    }

    @Override
    public int hashCode() {
        int result = bound.hashCode();
        result = 31 * result + from.hashCode();
        result = 31 * result + till.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TotalLargerByPeriodSamePayment{" +
                "from=" + from +
                ", till=" + till +
                ", bound=" + bound +
                '}';
    }
}
