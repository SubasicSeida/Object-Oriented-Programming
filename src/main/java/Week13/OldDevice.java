package Week13;

interface OldDevice {
    void operateOldFunction();
}

interface NewDevice {
    void operateNewFunction();
}

class OldDeviceImpl implements OldDevice {
    @Override
    public void operateOldFunction() {
        System.out.println("Operating old device function.");
    }
}

class NewDeviceImpl implements NewDevice {
    @Override
    public void operateNewFunction() {
        System.out.println("Operating new device function.");
    }
}

class DeviceAdapter implements NewDevice {
    private final OldDevice oldDevice;

    public DeviceAdapter(OldDevice oldDevice) {
        this.oldDevice = oldDevice;
    }

    @Override
    public void operateNewFunction() {
        System.out.println("Adapting old device function to new device function...");
        oldDevice.operateOldFunction();
    }
}

class Main3 {
    public static void main(String[] args) {
        // Using the NewDevice interface with a NewDeviceImpl
        NewDevice newDevice = new NewDeviceImpl();
        System.out.println("Using NewDeviceImpl:");
        newDevice.operateNewFunction();

        OldDevice oldDevice = new OldDeviceImpl();
        NewDevice adapter = new DeviceAdapter(oldDevice);
        System.out.println("Using OldDeviceImpl through DeviceAdapter:");
        adapter.operateNewFunction();
    }
}

