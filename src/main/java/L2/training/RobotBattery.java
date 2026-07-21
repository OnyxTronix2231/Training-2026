package L2.training;

public class RobotBattery {
    private double capacity;
    private double currentCharge;
    private boolean enabled;

    public RobotBattery(double capacity, double currentCharge, boolean enabled) {
        this.capacity = capacity;
        this.currentCharge = currentCharge;
        this.enabled = enabled;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(double currentCharge) {
        this.currentCharge = currentCharge;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void charge(double amount) {
        currentCharge += amount;
    }

    public void use(double amount) {
        currentCharge -= amount;
    }
    public double getChargePercentage() {
        return (currentCharge / capacity)*100;
    }

    public void printState() {
        System.out.println("Capacity: " + capacity);
        System.out.println("Current charge: " + currentCharge);
        System.out.println("Charge percentage: " + getChargePercentage() + "%");
        System.out.println("Enabled: " + enabled);
    }

    public static RobotBattery combine(
            RobotBattery firstBattery,
            RobotBattery secondBattery) {

        double combinedCapacity =
                firstBattery.capacity + secondBattery.capacity;

        double combinedCharge =
                firstBattery.currentCharge + secondBattery.currentCharge;

        boolean combinedEnabled =
                firstBattery.enabled && secondBattery.enabled;

        return new RobotBattery(
                combinedCapacity,
                combinedCharge,
                combinedEnabled
        );
    }

    public static void main(String[] args) {
        RobotBattery firstBattery =
                new RobotBattery(200.0, 80.0, true);

        RobotBattery secondBattery =
                new RobotBattery(300.0, 150.0, true);

        firstBattery.charge(20.0);
        secondBattery.use(30.0);

        RobotBattery combinedBattery =
                RobotBattery.combine(firstBattery, secondBattery);

        System.out.println("First battery:");
        firstBattery.printState();

        System.out.println("Second battery:");
        secondBattery.printState();

        System.out.println("Combined battery:");
        combinedBattery.printState();
    }
}