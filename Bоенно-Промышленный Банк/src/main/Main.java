package main;

import main.model.Client;
import main.model.Payment;
import main.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static Client clients[] = new Client[]{
            new Client(),
            new Client(),
            new Client()
    };

    private static Product products[] = new Product[]{
            new Product("MTS"),
            new Product("Moloko"),
            new Product("Film"),
            new Product("Avto"),
    };

    private static Payment payments[] = new Payment[]{
            new Payment(new BigDecimal(5000-1), Instant.now(), ranProd(), ranCli()),
            new Payment(new BigDecimal(456), Instant.now(), ranProd(), ranCli())
    };

    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();

        System.out.println(Arrays.deepToString(payments));

        for (Payment p : payments)
            bankSystem.proccess(p);

        System.out.println(Arrays.deepToString(payments));

        System.out.println(PaymentDAO.getInstance());
    }

    private static Product ranProd() {
        return products[new Random().nextInt(products.length)];
    }

    private static Client ranCli() {
        return clients[new Random().nextInt(clients.length)];
    }
}
