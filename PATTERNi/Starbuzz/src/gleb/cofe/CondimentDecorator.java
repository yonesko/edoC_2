package gleb.cofe;

public abstract class CondimentDecorator extends Beverage {
    protected final Beverage base;

    public CondimentDecorator(Beverage base) {
        this.base = base;
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", " + description;
    }

    @Override
    public double cost() {
        return base.cost() + cost;
    }
}
