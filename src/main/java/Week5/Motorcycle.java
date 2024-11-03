package Week5;

public class Motorcycle extends Vehicle implements Repairable{
    public Motorcycle(String modelName) {
        super(modelName);
    }

    public Motorcycle(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }

    @Override
    public String service() {
        return "Performing service on " + getModelName() +
                ": lubricating the chain and tuning the engine. Current mileage: " + getMileage() +
                ", health: " + getHealth() + ".";
    }

    @Override
    public int expectedLifespan() {
        int lifespan = 50000;
        if (getHealth() < 40) {
            lifespan -= 5000;
        }
        return lifespan;
    }

    public void race(int raceMiles) {
        setMileage(getMileage() + raceMiles);
        setHealth(getHealth() - (raceMiles / 10));

        if (getHealth() < 0) {
            setHealth(0);
        }
    }

    public String repair() {
        return "Chain lubricated and engine tuned for " + getModelName();
    }
}
