package main.pizzastore;

import main.pizza.NYCheesePizza;
import main.pizza.Pizza;

import java.util.Objects;

public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (Objects.equals(type, "cheese"))
            return new NYCheesePizza();
        return null;
    }
}
