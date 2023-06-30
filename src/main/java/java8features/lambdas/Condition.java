package java8features.lambdas;

@FunctionalInterface
public interface Condition {
    boolean satisfy(Person person);
}
