package Week5;

public class Truck extends Vehicle implements Repairable{
    public Truck(String modelName) {
        super(modelName);
    }

    public Truck(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }

    @Override
    public String service() {
        return "Performing service on " + getModelName() + ": checking brakes and changing oil. Current mileage: "
                + getMileage() + ", health: " + getHealth() + ".";
    }

    @Override
    public int expectedLifespan() {
        int lifespan = 300000;
        if (getHealth() < 30) {
            lifespan -= 20000;
        }
        return lifespan;
    }

    @Override
    public String repair() {
        return "Engine overhauled and tires replaced for " + getModelName() + ".";
    }

    public void haul(int loadWeight) {
        if (loadWeight > 5000) {
            setHealth(getHealth() - (loadWeight / 100));
        } else {
            setHealth(getHealth() - (loadWeight / 200));
        }

        if (getHealth() < 0) {
            setHealth(0);
        }
    }
}
