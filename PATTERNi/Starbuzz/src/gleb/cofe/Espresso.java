package gleb.cofe;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
        cost = 3;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 2;
                break;
            case MIDIUM:
                cost = 3;
                break;
            case BIG:
                cost  = 4;
                break;
        }
    }
}
