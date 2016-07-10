package gleb;

public class Waitress {
    private MenuComponent menus;

    public Waitress(MenuComponent menus) {
        this.menus = menus;
    }

    public void printMenu() {
        System.out.println(menus);
    }

}
