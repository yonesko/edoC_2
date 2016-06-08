package gleb;

import java.util.Iterator;

public class Waitress {
    private PancakeHouseMenu pancakeHouseMenu;
    private DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeItr = pancakeHouseMenu.iterator();
        Iterator<MenuItem> dinerItr = dinerMenu.iterator();
        System.out.println("BREAKFAST");
        printMenu(pancakeItr);
        System.out.println("LAUNCH");
        printMenu(dinerItr);
    }

    private void printMenu(Iterator<MenuItem> itemIterator) {
        String out = "%s, %.2f -- %s";
        while (itemIterator.hasNext()) {
            MenuItem item = itemIterator.next();

            System.out.println(String.format(out, item.getName(), item.getPrice(), item.getDescription()));
        }
    }
}
