package gleb.cofe;

public class Mocha extends CondimentDecorator {


    public Mocha(Beverage base) {
        super(base);
        description = "Mocha";
        cost = 0.2;
    }

    @Override
    public void setSize(Size size) {
        super.setSize(size);
        switch (size) {
            case LITTLE:
                cost = 0.1;
                break;
            case MIDIUM:
                cost = 0.2;
                break;
            case BIG:
                cost  = 0.3;
                break;
        }
    }
}
