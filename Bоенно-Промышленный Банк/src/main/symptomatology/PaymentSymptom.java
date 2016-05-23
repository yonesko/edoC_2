package main.symptomatology;

import main.model.Payment;

import java.util.function.Predicate;

/**
 * Created by gleb on 06.05.16.
 */
public interface PaymentSymptom extends Predicate<Payment> {
}
