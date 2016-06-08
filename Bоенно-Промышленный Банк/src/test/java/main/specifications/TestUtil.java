package main.specifications;

import main.data.model.Client;
import main.data.model.Product;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by gleb on 31.05.16.
 */
public class TestUtil {
    private static Client clients[] = new Client[]{
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client()
    };

    private static Product products[] = new Product[]{
            new Product("MTS"),
            new Product("Mils"),
            new Product("Film"),
            new Product("Car"),
            new Product("Food"),
            new Product("Pizza"),
            new Product("Tourism"),
            new Product("Music"),
    };

    public static Product ranProd() {
        return products[new Random().nextInt(products.length)];
    }

    public static BigDecimal ranValue() {
        return new BigDecimal(new Random().nextInt(5_000));
    }

    public static Client ranCli() {
        return clients[new Random().nextInt(clients.length)];
    }
}
