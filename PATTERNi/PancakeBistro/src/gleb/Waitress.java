package gleb;

import java.util.ArrayList;
import java.util.List;

public class Waitress {
    private List<Menu> menu;

    public Waitress() {
        menu = new ArrayList<>();
    }

    public boolean add(Menu menuItems) {
        return menu.add(menuItems);
    }

    public void printMenu() {
        for (Menu menuItems : menu)
            printMenu(menuItems);
    }

    private void printMenu(Menu menu) {
        String out = "%s, %.2f -- %s";

        for (MenuItem item : menu)
            System.out.println(String.format(out, item.getName(), item.getPrice(), item.getDescription()));
    }
}
