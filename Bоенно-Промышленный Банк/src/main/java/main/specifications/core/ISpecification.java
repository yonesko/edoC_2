package main.specifications.core;

/**
 * Created by gleb on 23.05.16.
 */
public interface ISpecification <T> {
    boolean isSatisfiedBy(T t);
    ISpecification and(ISpecification specification);
    ISpecification or(ISpecification specification);
    ISpecification not();
}
