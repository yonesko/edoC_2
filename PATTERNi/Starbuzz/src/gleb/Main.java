package gleb;

import gleb.cofe.Beverage;
import gleb.cofe.DarkRoast;
import gleb.cofe.Mocha;
import gleb.cofe.Whip;

public class Main {

    public static void main(String[] args) {

        Beverage beverage = new DarkRoast(5);

        Beverage b2 = new Mocha(beverage);
        Beverage b3 = new Whip(b2);

        System.out.println(b3.cost());
    }
}
