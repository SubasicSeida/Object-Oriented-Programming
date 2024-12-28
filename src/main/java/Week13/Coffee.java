package Week13;

import java.util.ArrayList;
import java.util.List;

class Coffee {
    private final String type;
    private final String size;
    private final List<String> toppings;

    private Coffee(CoffeeBuilder builder) {
        this.type = builder.getType();
        this.size = builder.getSize();
        this.toppings = builder.getToppings();
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Coffee Type: " + type + ", Size: " + size + ", Toppings: " + toppings;
    }

    interface CoffeeBuilder {
        CoffeeBuilder buildType(String type);
        CoffeeBuilder buildSize(String size);
        CoffeeBuilder addTopping(String topping);
        Coffee build();
        String getType();
        String getSize();
        List<String> getToppings();
    }

    public static class EspressoBuilder implements CoffeeBuilder {
        private String type = "Espresso";
        private String size;
        private List<String> toppings = new ArrayList<>();

        @Override
        public CoffeeBuilder buildType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public CoffeeBuilder buildSize(String size) {
            this.size = size;
            return this;
        }

        @Override
        public CoffeeBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        @Override
        public Coffee build() {
            return new Coffee(this);
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getSize() {
            return size;
        }

        @Override
        public List<String> getToppings() {
            return toppings;
        }
    }

    public static class LatteBuilder implements CoffeeBuilder {
        private String type = "Latte";
        private String size;
        private List<String> toppings = new ArrayList<>();

        @Override
        public CoffeeBuilder buildType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public CoffeeBuilder buildSize(String size) {
            this.size = size;
            return this;
        }

        @Override
        public CoffeeBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        @Override
        public Coffee build() {
            return new Coffee(this);
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getSize() {
            return size;
        }

        @Override
        public List<String> getToppings() {
            return toppings;
        }
    }

    public static class CappuccinoBuilder implements CoffeeBuilder {
        private String type = "Cappuccino";
        private String size;
        private List<String> toppings = new ArrayList<>();

        @Override
        public CoffeeBuilder buildType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public CoffeeBuilder buildSize(String size) {
            this.size = size;
            return this;
        }

        @Override
        public CoffeeBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        @Override
        public Coffee build() {
            return new Coffee(this);
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public String getSize() {
            return size;
        }

        @Override
        public List<String> getToppings() {
            return toppings;
        }
    }
}

class CoffeeDirector {
    public Coffee constructCoffee(Coffee.CoffeeBuilder builder) {
        return builder.build();
    }
}

class Main4 {
    public static void main(String[] args) {
        CoffeeDirector director = new CoffeeDirector();

        Coffee espresso = director.constructCoffee(
                new Coffee.EspressoBuilder()
                        .buildSize("Small")
                        .addTopping("Extra Shot")
                        .addTopping("Sugar"));
        System.out.println(espresso);

        Coffee latte = director.constructCoffee(
                new Coffee.LatteBuilder()
                        .buildSize("Medium")
                        .addTopping("Vanilla Syrup")
                        .addTopping("Whipped Cream"));
        System.out.println(latte);

        Coffee cappuccino = director.constructCoffee(
                new Coffee.CappuccinoBuilder()
                        .buildSize("Large")
                        .addTopping("Cinnamon"));
        System.out.println(cappuccino);
    }
}

