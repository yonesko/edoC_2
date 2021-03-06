package main.ingredients;

import main.ingredients.cheese.Cheese;
import main.ingredients.cheese.ReggianoCheese;
import main.ingredients.clam.Clams;
import main.ingredients.clam.FreshClams;
import main.ingredients.dough.Dough;
import main.ingredients.dough.ThinCrustDough;
import main.ingredients.pepperoni.Pepperoni;
import main.ingredients.pepperoni.SlicedPepperoni;
import main.ingredients.sauce.MarinaraSauce;
import main.ingredients.sauce.Sauce;
import main.ingredients.veggies.*;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
