package gleb.cofe;

public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
        cost = 2;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 1;
                break;
            case MIDIUM:
                cost = 2;
                break;
            case BIG:
                cost  = 4;
                break;
        }
    }
}
