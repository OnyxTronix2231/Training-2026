package frc.robot.logging.motorSimulation;

public class SimulatedSensors {
    private static boolean simulatedShooterSensorSeeing;

    public SimulatedSensors() {
        simulatedShooterSensorSeeing = false;
    }

    public static boolean isSimulatedShooterSensorSeeing() {
        return simulatedShooterSensorSeeing;
    }

    public static void setSimulatedShooterSensorSeeing(boolean simulatedShooterSensorSeeing) {
        SimulatedSensors.simulatedShooterSensorSeeing = simulatedShooterSensorSeeing;
    }
}
