package main.model;

import main.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by gleb on 06.05.16.
 */
public class Payment {
    private BigDecimal value;
    private Instant date;
    private Product product;
    private PaymentStatus status;
    private Client client;

    public Payment(BigDecimal value, Instant date, Product product, Client client) {
        this.value = value;
        this.date = date;
        this.product = product;
        this.status = PaymentStatus.initial;
        this.client = client;
    }

    public void moveTo(PaymentStatus status) {
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Instant getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return product.equals(payment.product) && client.equals(payment.client);

    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value +
                ", date=" + date +
                ", product=" + product +
                ", status=" + status +
                ", client=" + client +
                '}';
    }
}
