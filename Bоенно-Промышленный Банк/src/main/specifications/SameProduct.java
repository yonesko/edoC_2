package main.specifications;

import main.data.model.Payment;
import main.data.model.Product;


public class SameProduct extends CompositeSpecification<Payment> {
    private Product product;

    public SameProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean isSatisfiedBy(Payment payment) {
        return payment.getProduct().equals(product);
    }
}
