package gleb.cofe;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
        cost = 4;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 3;
                break;
            case MIDIUM:
                cost = 4;
                break;
            case BIG:
                cost  = 5;
                break;
        }
    }

}
