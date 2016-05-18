package main.pizza;

import main.ingredients.cheese.Cheese;
import main.ingredients.clam.Clams;
import main.ingredients.dough.Dough;
import main.ingredients.pepperoni.Pepperoni;
import main.ingredients.sauce.Sauce;
import main.ingredients.veggies.Veggies;

/**
 * Created by gleb on 11.05.16.
 */
public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clam;

    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 min at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in a box");
    }

    public String getName() {
        return name;
    }
}
