package gleb.cofe;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
        cost = 1;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 0.6;
                break;
            case MIDIUM:
                cost = 1;
                break;
            case BIG:
                cost = 2;
                break;
        }
    }
}
