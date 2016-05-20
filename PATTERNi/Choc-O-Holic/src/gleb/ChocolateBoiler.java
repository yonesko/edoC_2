package gleb;

/**
 * Created by gleb on 19.05.16.
 */
public class ChocolateBoiler {
    private static ChocolateBoiler ourInstance = new ChocolateBoiler();
    private boolean empty;
    private boolean boiled;

    public static ChocolateBoiler getInstance() {
        return ourInstance;
    }

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public void fill() {
        if(isEmpty()) {
            empty = false;
            boiled = false;
        }
    }



    public void drain() {
        if(!isEmpty() && isBoiled()) {
            empty = true;
        }
    }

    public void boil() {
        if(!isEmpty() && !isBoiled())
            boiled = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
