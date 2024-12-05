package Week7;

@FunctionalInterface
public interface DiscountStrategy {
    double applyDiscount(double price);
}
