package main;

import main.pizza.Pizza;
import main.pizzastore.NYPizzaStore;
import main.pizzastore.PizzaStore;

public class Main {
    public static void main(String...ar) {
        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");

    }
}
