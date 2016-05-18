package main.pizza;

import java.util.ArrayList;

/**
 * Created by gleb on 11.05.16.
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    public void prepare() {
        System.out.println("Preparing..." + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println(" " + toppings.get(i));
        }
    }

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
