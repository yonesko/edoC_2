package gleb.cofe;

public abstract class Beverage {
    protected String description = "Unknown Beverage";
    protected Size size = Size.MIDIUM;
    protected double cost;

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double cost() {
        return cost;
    };

}
