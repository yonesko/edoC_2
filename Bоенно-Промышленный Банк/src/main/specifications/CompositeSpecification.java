package main.specifications;


public abstract class CompositeSpecification <T> implements ISpecification<T> {
    @Override
    public abstract boolean isSatisfiedBy(T t) ;

    @Override
    public ISpecification and(ISpecification specification) {
        return new AndSpec(this, specification);
    }

    @Override
    public ISpecification or(ISpecification specification) {
        return new OrSpec(this, specification);
    }

    @Override
    public ISpecification not() {
        return new NotSpec(this);
    }
}
