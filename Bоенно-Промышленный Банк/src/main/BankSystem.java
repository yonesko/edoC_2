package main;

import main.data.PaymentDAO;
import main.data.model.Payment;
import main.data.model.PaymentStatus;
import main.specifications.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

public class BankSystem {
    private Map<ISpecification<Payment>, FraudSpecRep> mFraudCount;

    public BankSystem() {
        mFraudCount = new HashMap<>();
        mFraudCount.put(new TotalLargerByPeriodSameProduct(
                new BigDecimal("5000"),
                LocalTime.of(9, 0),
                LocalTime.of(23, 0)), new FraudSpecRep());
        mFraudCount.put(new TotalLargerByPeriodSameProduct(
                new BigDecimal("1000"),
                LocalTime.of(23, 0),
                LocalTime.of(9, 0)), new FraudSpecRep());
    }

    public void proccess(Payment payment) {
        PaymentDAO.getInstance().add(payment);
        if (isFraud(payment)) {
            payment.moveTo(PaymentStatus.Tребует_подтверждения);
        } else {
            payment.moveTo(PaymentStatus.rотов_к_проведению);
        }
    }

    private boolean isFraud(Payment payment) {
        for (Map.Entry<ISpecification<Payment>, FraudSpecRep> e : mFraudCount.entrySet())
            if (e.getKey().isSatisfiedBy(payment)) {
                e.getValue().increment();
                e.getValue().add(payment);
                return true;
            }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Violators:\n");
        for (Map.Entry<ISpecification<Payment>, FraudSpecRep> e : mFraudCount.entrySet()) {
            sb.append(e.getKey());
            sb.append(":");
            sb.append(e.getValue().getCount());
            sb.append('\n');
            for (Payment payment : e.getValue().getViolators()) {
                sb.append('\t');
                sb.append(payment);
                sb.append('\n');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class FraudSpecRep {
    private int count;
    private List<Payment> violators;

    public int getCount() {
        return count;
    }

    public List<Payment> getViolators() {
        return new ArrayList<>(violators);
    }

    public FraudSpecRep() {
        count = 0;
        violators = new ArrayList<>();
    }
    public void increment() {
        count++;
    }

    public boolean add(Payment payment) {
        return violators.add(payment);
    }
}