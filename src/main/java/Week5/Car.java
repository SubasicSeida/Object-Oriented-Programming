package Week5;

public class Car extends Vehicle implements Repairable{
    public Car(String modelName) {
        super(modelName);
    }

    public Car(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }

    @Override
    public String service() {
        return "Performing service on " + getModelName() + ": checking engine and changing oil. Current mileage: "
                + getMileage() + ", health: " + getHealth() + ".";
    }

    @Override
    public int expectedLifespan() {
        int lifespan = 150000;
        if (getHealth() < 30) {
            lifespan -= 10000;
        }
        return lifespan;
    }

    @Override
    public String repair() {
        return "Engine tuned and oil changed for " + getModelName() + ".";
    }

    public void drive(int miles) {
        if (miles < 0) {
            throw new IllegalArgumentException("Miles driven cannot be negative.");
        }
        setMileage(getMileage() + miles);
        setHealth(getHealth() - (miles / 100));
        if (getHealth() < 0) {
            setHealth(0);
        }
    }
}
