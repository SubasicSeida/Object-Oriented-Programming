package Week13;

interface TrafficLightState {
    void transitionToRed(TrafficLightContext context);
    void transitionToGreen(TrafficLightContext context);
    void transitionToYellow(TrafficLightContext context);
}

class RedLightState implements TrafficLightState {
    @Override
    public void transitionToRed(TrafficLightContext context) {
        System.out.println("Traffic light is already Red.");
    }

    @Override
    public void transitionToGreen(TrafficLightContext context) {
        System.out.println("Transitioning from Red to Green.");
        context.setState(new GreenLightState());
    }

    @Override
    public void transitionToYellow(TrafficLightContext context) {
        System.out.println("Cannot transition directly from Red to Yellow.");
    }
}

class YellowLightState implements TrafficLightState {
    @Override
    public void transitionToRed(TrafficLightContext context) {
        System.out.println("Transitioning from Yellow to Red.");
        context.setState(new RedLightState());
    }

    @Override
    public void transitionToGreen(TrafficLightContext context) {
        System.out.println("Cannot transition directly from Yellow to Green.");
    }

    @Override
    public void transitionToYellow(TrafficLightContext context) {
        System.out.println("Traffic light is already Yellow.");
    }
}

class GreenLightState implements TrafficLightState {
    @Override
    public void transitionToRed(TrafficLightContext context) {
        System.out.println("Cannot transition directly from Green to Red.");
    }

    @Override
    public void transitionToGreen(TrafficLightContext context) {
        System.out.println("Traffic light is already Green.");
    }

    @Override
    public void transitionToYellow(TrafficLightContext context) {
        System.out.println("Transitioning from Green to Yellow.");
        context.setState(new YellowLightState());
    }
}

class TrafficLightContext {
    private TrafficLightState state;

    public TrafficLightContext() {
        this.state = new RedLightState(); // Initial state is Red
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void transitionToRed() {
        state.transitionToRed(this);
    }

    public void transitionToGreen() {
        state.transitionToGreen(this);
    }

    public void transitionToYellow() {
        state.transitionToYellow(this);
    }
}

class TrafficLightController {
    public static void main(String[] args) {
        TrafficLightContext context = new TrafficLightContext();

        context.transitionToGreen();
        context.transitionToYellow();
        context.transitionToRed();
        context.transitionToYellow();
    }
}

