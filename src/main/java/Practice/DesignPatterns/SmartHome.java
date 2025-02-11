package Practice.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

interface SmartDevice {
    void operate();
    String getName();
}

class SmartLight implements SmartDevice {
    private String color;
    private int brightness;

    public SmartLight(SmartLightBuilder builder){
        this.color = builder.color;
        this.brightness = builder.brightness;
    }

    static class SmartLightBuilder {
        private String color;
        private int brightness;

        public SmartLightBuilder setColor(String color){
            this.color = color;
            return this;
        }

        public SmartLightBuilder setBrightness(int brightness){
            this.brightness = brightness;
            return this;
        }

        public SmartLight build(){
            return new SmartLight(this);
        }
    }

    @Override
    public void operate() {
        System.out.println("Smart Light is turned ON.");
    }

    public String getName() { return "SmartLight"; }
}

class SmartThermostat implements SmartDevice {
    private int temperature;
    private String mode;
    public SmartThermostat(SmartThermostatBuilder builder){
        this.temperature = builder.temperature;
        this.mode = builder.mode;
    }

    static class SmartThermostatBuilder {
        private int temperature;
        private String mode;

        public SmartThermostatBuilder setTemperature(int temperature){
            this.temperature = temperature;
            return this;
        }

        public SmartThermostatBuilder setMode(String mode){
            this.mode = mode;
            return this;
        }

        public SmartThermostat build(){
            return new SmartThermostat(this);
        }
    }

    @Override
    public void operate() {
        System.out.println("Smart Thermostat is set to 22°C.");
    }

    public String getName() { return "SmartThermostat"; }

}

class SecurityCamera implements SmartDevice {
    public SecurityCamera(){}
    public void operate() {
        System.out.println("Security Camera is turned ON.");
    }
    public String getName() { return "Security Camera"; }
}

class SmartDeviceFactory {
    public static SmartDevice getSmartDevice(String type){
        if(type.equalsIgnoreCase("Smart Light")) return new SmartLight.SmartLightBuilder()
                .setColor("White")
                .setBrightness(80)
                .build();
        else if(type.equalsIgnoreCase("Smart Thermostat")) return new SmartThermostat.SmartThermostatBuilder()
                .setTemperature(22)
                .build();
        else if(type.equalsIgnoreCase("Security Camera")) return new SecurityCamera();
        else throw new IllegalArgumentException("No such smart device.");
    }
}

class SmartHomeHub {
    private static SmartHomeHub instance;
    private List<SmartDevice> smartDevices = new ArrayList<>();

    private SmartHomeHub(){};

    public static SmartHomeHub getInstance(){
        if(instance == null){
            instance = new SmartHomeHub();
        }
        return instance;
    }

    public void addDevice(SmartDevice device) {
        smartDevices.add(device);
        System.out.println(device.getName() + " added to Smart Home Hub.");
    }

    public void removeDevice(String deviceName) {
        smartDevices.removeIf(device -> device.getName().equalsIgnoreCase(deviceName));
        System.out.println(deviceName + " removed from Smart Home Hub.");
    }

    public void controlAllDevices() {
        System.out.println("Controlling all devices in the Smart Home Hub:");
        for (SmartDevice device : smartDevices) {
            device.operate();
        }
    }
}
