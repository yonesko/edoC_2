package gleb;

public class Main {

    public static void main(String[] args) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        CafeMenu cafeMenu = new CafeMenu();

        Waitress waitress = new Waitress();
        waitress.add(pancakeHouseMenu);
        waitress.add(dinerMenu);
        waitress.add(cafeMenu);

        waitress.printMenu();
    }
}
