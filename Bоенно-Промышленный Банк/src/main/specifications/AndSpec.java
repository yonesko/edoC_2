package main.specifications;


public class AndSpec <T> extends CompositeSpecification<T> {
    private ISpecification a;
    private ISpecification b;

    public AndSpec(ISpecification a, ISpecification b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return a.isSatisfiedBy(t) && b.isSatisfiedBy(t);
    }
}
