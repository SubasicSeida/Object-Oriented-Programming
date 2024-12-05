package Week7;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class DiscountEngine {
    public static DiscountStrategy percentageDiscount(double percentage) {
        return price -> price - (price * percentage / 100);
    }

    public static DiscountStrategy fixedDiscount(double discountAmount) {
        return price -> price - discountAmount;
    }

    public static DiscountStrategy bulkDiscount(int threshold, double discountAmount) {
        return price -> price >= threshold ? price - discountAmount : price;
    }

    public static double applyDiscountToOrder(List<Product> products, DiscountStrategy discountStrategy) {
        double totalPrice = 0;

        for (Product product : products) {
            double discountedPrice = discountStrategy.applyDiscount(product.getPrice());
            totalPrice += discountedPrice * product.getQuantity();
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1000, 1));
        products.add(new Product("Headphones", 150, 2));
        products.add(new Product("Smartphone", 800, 1));

        DiscountStrategy percentageDiscount = percentageDiscount(10);
        double totalWithPercentageDiscount = applyDiscountToOrder(products, percentageDiscount);
        System.out.println("Total with Percentage Discount: " + totalWithPercentageDiscount);

        DiscountStrategy fixedDiscount = fixedDiscount(50);
        double totalWithFixedDiscount = applyDiscountToOrder(products, fixedDiscount);
        System.out.println("Total with Fixed Discount: " + totalWithFixedDiscount);

        DiscountStrategy bulkDiscount = bulkDiscount(500, 100);
        double totalWithBulkDiscount = applyDiscountToOrder(products, bulkDiscount);
        System.out.println("Total with Bulk Discount: " + totalWithBulkDiscount);
    }
}
