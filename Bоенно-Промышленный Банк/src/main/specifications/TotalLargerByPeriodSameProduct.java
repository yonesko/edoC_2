package main.specifications;

import main.data.PaymentDAO;
import main.data.model.Payment;

import java.math.BigDecimal;
import java.time.LocalTime;

public class TotalLargerByPeriodSameProduct extends CompositeSpecification<Payment> {
    private BigDecimal bound;
    private LocalTime from;
    private LocalTime till;
    private PaymentDAO paymentDAO = PaymentDAO.getInstance();

    public TotalLargerByPeriodSameProduct(BigDecimal bound, LocalTime from, LocalTime till) {
        this.bound = bound;
        this.from = from;
        this.till = till;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        ISpecification spec;

        spec = new TimeBounds(from, till)
                .and(new SameProduct(payment.getProduct()));

        return paymentDAO.sum(paymentDAO.filter(spec)).compareTo(bound) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalLargerByPeriodSameProduct that = (TotalLargerByPeriodSameProduct) o;

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
        return "TotalLargerByPeriodSameProduct{" +
                "from=" + from +
                ", till=" + till +
                ", bound=" + bound +
                '}';
    }
}
