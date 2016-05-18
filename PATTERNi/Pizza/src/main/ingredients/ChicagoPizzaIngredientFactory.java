package main.ingredients;

import main.ingredients.cheese.Cheese;
import main.ingredients.cheese.Mozzarella;
import main.ingredients.clam.Clams;
import main.ingredients.clam.FrozenClams;
import main.ingredients.dough.Dough;
import main.ingredients.dough.ThickCrustDough;
import main.ingredients.pepperoni.Pepperoni;
import main.ingredients.pepperoni.SlicedPepperoni;
import main.ingredients.sauce.PlumTomatoSauce;
import main.ingredients.sauce.Sauce;
import main.ingredients.veggies.*;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new Mozzarella();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Spinach(), new EggPlant(), new BlackOlives(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        return new FrozenClams();
    }
}
