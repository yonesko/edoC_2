package main.data;

import main.data.model.Client;
import main.data.model.Payment;
import main.data.model.Product;
import main.specifications.ISpecification;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gleb on 06.05.16.
 */
public class PaymentDAO {
    private List<Payment> bd = new ArrayList<>();
    private static final PaymentDAO instance = new PaymentDAO();

    public static void main(String...args) {
        Client client;
    }

    @Override
    public String toString() {
        bd.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%5s|%7s|%10s|%25s|%30s|%15s\n",
                "#", "Client", "Product", "Status", "Date", "Value"));

        for (int i = 0; i < bd.size(); i++) {
            Payment p = bd.get(i);
            sb.append(String.format("%5d|%7d|%10s|%25s|%30s|%15s\n",
                    i,
                    p.getClient().getPersonalAccount(),
                    p.getProduct().getTitle(),
                    p.getStatus(),
                    p.getDate(),
                    p.getValue()));
        }
        return sb.toString();
    }

    public static PaymentDAO getInstance() {
        return instance;
    }

    public boolean add(Payment payment) {
        return bd.add(payment);
    }

    public List<Payment> byPeriod(Instant a, Instant b) {
        List<Payment> result;
        result = bd.stream()
                .filter(p -> ChronoUnit.NANOS.between(a, p.getDate()) >= 0)
                .filter(p -> ChronoUnit.NANOS.between(p.getDate(), b) >= 0)
                .collect(Collectors.toList());
        return result;
    }

    public BigDecimal sum(List<Payment> payments) {
        BigDecimal result = new BigDecimal(0);
        for (Payment payment : payments)
            result = result.add(payment.getValue());
        return result;
    }

    public List<Payment> byClient(Client client) {
        List<Payment> result = new ArrayList<>();
        result = bd.stream()
                .filter(p -> p.getClient().equals(client))
                .collect(Collectors.toList());
        return result;
    }

    public List<Payment> byProduct(Product product) {
        List<Payment> result;
        result = bd.stream()
                .filter(p -> p.getProduct().equals(product))
                .collect(Collectors.toList());
        return result;
    }

    public List<Payment> byPayment(Payment payment) {
        return bd.stream()
                .filter(p -> p.equals(payment))
                .collect(Collectors.toList());
    }

    public List<Payment> sameDay(Payment payment) {
        return bd.stream().filter(p -> isSameDay(p, payment)).collect(Collectors.toList());
    }

    public List<Payment> filter(ISpecification<Payment> specification) {
        List<Payment> result = new ArrayList<>();

        for (Payment payment : bd)
            if (specification.isSatisfiedBy(payment))
                result.add(payment);

        return result;
    }

    private boolean isSameDay(Payment p1, Payment p2) {
        List<Payment> list;
        boolean resuly;

        if (p1.getDate().isAfter(p2.getDate())) {
            list = instance.byPeriod(p1.getDate().minus(1, ChronoUnit.DAYS), p1.getDate());
            resuly = list.contains(p2);
        }
        else {
            list = instance.byPeriod(p1.getDate().minus(1, ChronoUnit.DAYS), p2.getDate());
            resuly = list.contains(p1);
        }

        return resuly;
    }

    private PaymentDAO() {
    }
}
