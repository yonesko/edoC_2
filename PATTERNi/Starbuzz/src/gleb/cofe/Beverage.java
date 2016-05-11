package gleb.cofe;

public abstract class Beverage {
    protected String description;
    protected double cost;
    protected Beverage base;


    public String getDescription() {
        return description;
    }

    public Beverage(double cost, Beverage base) {
        this.cost = cost;
        this.base = base;
    }

    public Beverage(double cost) {
        this.cost = cost;
    }

    public Beverage(Beverage base) {
        this.base = base;
    }

    public double cost() {
        return cost + getBaseCost();
    }

    protected double getBaseCost() {
        return base == null ? 0 : base.cost();
    }
}
