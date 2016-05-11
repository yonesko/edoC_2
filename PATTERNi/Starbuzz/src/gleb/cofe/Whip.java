package gleb.cofe;

public class Whip extends CondimentDecorator {


    public Whip(Beverage base) {
        super(base);
        description = "Whip";
        cost = 0.4;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 0.3;
                break;
            case MIDIUM:
                cost = 0.4;
                break;
            case BIG:
                cost  = 0.5;
                break;
        }
    }
}
