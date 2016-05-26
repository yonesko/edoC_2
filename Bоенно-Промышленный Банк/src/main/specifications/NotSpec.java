package main.specifications;

public class NotSpec <T> extends CompositeSpecification <T> {
    private ISpecification a;

    public NotSpec(ISpecification a) {
        this.a = a;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return !a.isSatisfiedBy(t);
    }
}
