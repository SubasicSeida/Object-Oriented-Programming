package Week5;

abstract class Vehicle {
    private String modelName;
    private int mileage;
    private int health;

    public Vehicle(String modelName) {
        this.modelName = modelName;
        this.mileage = 0;
        this.health = 100;
    }

    public Vehicle(String modelName, int mileage, int health) {
        this.modelName = modelName;
        this.mileage = mileage;
        this.health = health;
    }

    public String getModelName() {
        return modelName;
    }

    public int getMileage() {
        return mileage;
    }

    public int getHealth() {
        return health;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean needsMaintenance() {
        return health < 70;
    }

    public int calculateRemainingLifespan() {
        int expectedLifespan = expectedLifespan();
        if (health < 50) {
            return (int) (expectedLifespan * ((double) health / 100));
        }
        return expectedLifespan - mileage;
    }

    public void simulateYear() {
        if (mileage > (expectedLifespan() / 2)) {
            setHealth(getHealth() - 5);
        }

        if (getHealth() < 0) {
            setHealth(0);
        }

        int remainingLifespan = calculateRemainingLifespan();
        System.out.println("Remaining lifespan after a year: " + remainingLifespan + " miles.");
    }

    public void performMaintenance(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            System.out.println(car.repair());
            car.drive(100);
            System.out.println("Car after maintenance: " + car.getMileage() +
                    " miles, " + car.getHealth() + " health.");
        } else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            System.out.println(truck.repair());
            truck.haul(6000);
            System.out.println("Truck after maintenance: " + truck.getHealth() + " health.");
        } else if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            System.out.println(motorcycle.repair());
            motorcycle.race(150);
            System.out.println("Motorcycle after maintenance: " + motorcycle.getMileage() +
                    " miles, " + motorcycle.getHealth() + " health.");
        }
    }

    public abstract String service();

    public abstract int expectedLifespan();
}
