package main.ingredients;

import main.ingredients.cheese.Cheese;
import main.ingredients.clam.Clams;
import main.ingredients.dough.Dough;
import main.ingredients.pepperoni.Pepperoni;
import main.ingredients.sauce.Sauce;
import main.ingredients.veggies.Veggies;

/**
 * Created by gleb on 18.05.16.
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClams();
}
