package main;

import main.model.Client;
import main.model.Payment;
import main.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        return "PaymentDAO{" +
                "bd=" + bd +
                '}';
    }

    public static PaymentDAO getInstance() {
        return instance;
    }

    public boolean add(Payment payment) {
        return bd.add(payment);
    }

    public List<Payment> byPeriod(Instant a, Instant b) {
        List<Payment> result = new ArrayList<>();
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
        List<Payment> result = new ArrayList<>();
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
