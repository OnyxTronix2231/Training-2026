package frc.robot.subsystems.lecture.shooter;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.lecture.shooter.ShooterConstants.SHOOTER_SENSOR_DEBOUNCE_TIME;

public class Shooter extends SubsystemBase {

    private final ShooterIO shooterIO;
    private final ShooterIO.ShooterOutputs shooterOutputs;

    // The IO reports the raw sensor, the subsystem filters it
    private final Debouncer sensorDebouncer = new Debouncer(SHOOTER_SENSOR_DEBOUNCE_TIME, Debouncer.DebounceType.kBoth);
    private boolean isSensorSeeing;

    public Shooter(ShooterIO shooterIO) {
        this.shooterIO = shooterIO;
        this.shooterOutputs = new ShooterIO.ShooterOutputs();

        this.shooterIO.updateOutputs(shooterOutputs);
    }

    @Override
    public void periodic() {
        shooterIO.updateOutputs(shooterOutputs);
        isSensorSeeing = sensorDebouncer.calculate(shooterOutputs.isSensorSeeing);

        log();
    }

    private void log() {
        shooterOutputs.masterMotorOutputs.log();
        shooterOutputs.followerMotorOutputs.log();

        Logger.recordOutput("Subsystems/Shooter/SensorRaw", isSensorSeeingRaw());
        Logger.recordOutput("Subsystems/Shooter/SensorFiltered", isSensorSeeing());
    }

    public boolean isSensorSeeingRaw() {
        return shooterOutputs.isSensorSeeing;
    }

    public boolean isSensorSeeing() {
        return isSensorSeeing;
    }

    public void setDutyCycle(double dutyCycle) {
        shooterIO.setDutyCycle(dutyCycle);
    }

    public double getRPS() {
        return shooterOutputs.masterMotorOutputs.getMotorAngularVelocityRotPerSec();
    }

    public double getAcceleration() {
        return shooterOutputs.masterMotorOutputs.getMotorAngularAccelerationRotPerSecSquared();
    }

    private static Shooter instance;

    public static void init(ShooterIO shooterIO) {
        if (instance == null) {
            instance = new Shooter(shooterIO);
        }
    }

    public static Shooter getInstance() {
        return instance;
    }
}
