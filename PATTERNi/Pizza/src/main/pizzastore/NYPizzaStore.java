package main.pizzastore;

import main.ingredients.NYPizzaIngredientFactory;
import main.pizza.CheesePizza;
import main.pizza.Pizza;

import java.util.Objects;

public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza result = null;
        if (Objects.equals(type, "cheese")) {
            result = new CheesePizza(new NYPizzaIngredientFactory());
            result.setName("New York Style Cheese Pizza");
        }
        return result;
    }
}
