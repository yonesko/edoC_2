package gleb;

public class Main {

    public static void main(String[] args) {
        Menu pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        Menu dinerMenu = new Menu("DINER MENU", "Lunch");
        Menu cafeMenu = new Menu("CAFE MENU", "Diner");
        Menu dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");

        MenuComponent allMenus = new Menu("ALL MENUS", "ALL MENUS");

        dinerMenu.add(new MenuItem("Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99));
        dinerMenu.add(new MenuItem("BLT",
                "Bacon with lettuce & tomato on whole wheat", false, 2.99));
        dinerMenu.add(new MenuItem("Soup of the day",
                "Soup of the day, with a side of potato salad", false, 3.29));
        dinerMenu.add(new MenuItem("Hotdog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese",
                false, 3.05));
        dinerMenu.add(new MenuItem("Steamed Veggies and Brown Rice",
                "Steamed vegetables over brown rice", true, 3.99));
        dinerMenu.add(new MenuItem("Pasta",
                "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
                true, 3.89));

        cafeMenu.add(new MenuItem("Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true, 3.99));
        cafeMenu.add(new MenuItem("Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false, 3.69));
        cafeMenu.add(new MenuItem("Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true, 4.29));

        dessertMenu.add(new MenuItem("Apple pie", "Apple pie with a flakey crust, topped with vanilla ice cream", true, 1.59));

        pancakeHouseMenu.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast", true, 2.99));
        pancakeHouseMenu.add(new MenuItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99));
        pancakeHouseMenu.add(new MenuItem("Blueberry Pancakes", "Pancakes made with fresh blueberries", true, 3.49));
        pancakeHouseMenu.add(new MenuItem("Waffles", "Waffles, with your choice of blueberries or strawberries", true, 3.59));

        dinerMenu.add(dessertMenu);

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }

}
