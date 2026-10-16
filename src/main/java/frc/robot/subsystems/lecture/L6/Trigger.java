package frc.robot.subsystems.lecture.L6;

import edu.wpi.first.math.filter.Debouncer;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;
import org.littletonrobotics.junction.Logger;

import java.util.List;

import static frc.robot.subsystems.lecture.L6.TriggerConstants.*;

public class Trigger extends VelocityBasedSubsystem {
    private final TriggerIO.TriggerInputs triggerInputs;
    private final TriggerIO triggerIO;

    public enum WantedState {
        IDLE,
        MOVE_BY_SPEED,
        TEST
    }

    public enum SystemState {
        IDLING,
        MOVING_BY_SPEED,
        TESTING
    }

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    private double wantedSpeed;
    private double wantedRPS;

    // The IO reports the raw sensor, the subsystem filters it
    private final Debouncer sensorDebouncer = new Debouncer(TRIGGER_SENSOR_DEBOUNCE_TIME, Debouncer.DebounceType.kBoth);
    private boolean isSensorSeeing;

    private final Debouncer isOnTargetDebouncer = new Debouncer(TRIGGER_DEBOUNCE_TIME);

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double wantedSpeed) {
        this.wantedState = wantedState;
        this.wantedSpeed = wantedSpeed;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public Trigger(TriggerIO triggerIO) {
        this.triggerInputs = new TriggerIO.TriggerInputs();
        this.triggerIO = triggerIO;

        this.triggerIO.updateInputs(triggerInputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = SystemState.IDLING;

        this.wantedSpeed = 0;
        this.wantedRPS = 0;
    }

    @Override
    public void periodic() {
        triggerIO.updateInputs(triggerInputs);
        isSensorSeeing = sensorDebouncer.calculate(triggerInputs.isSensorSeeing);

        systemState = handleStateTransition();
        applyStates();
        log();

        previousSystemState = systemState;
    }

    private SystemState handleStateTransition() {
        return switch(wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
            case TEST -> SystemState.TESTING;
        };
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING -> idleState();
            case MOVING_BY_SPEED -> moveBySpeed();
            case TESTING -> runTest();
        }
    }

    protected void idleState() {
        triggerIO.setDutyCycle(0);
    }

    protected void moveBySpeed() {
       triggerIO.setDutyCycle(wantedSpeed);
    }

    private void log() {
        triggerInputs.triggerMotorInputs.log();

        Logger.recordOutput("Subsystems/Trigger/WantedState", wantedState);
        Logger.recordOutput("Subsystems/Trigger/SystemState", systemState);
        Logger.recordOutput("Subsystems/Trigger/PreviousSystemState", previousSystemState);

        Logger.recordOutput("Subsystems/Trigger/SensorRaw", isSensorSeeingRaw());
        Logger.recordOutput("Subsystems/Trigger/SensorFiltered", isSensorSeeing());

        Logger.recordOutput("Subsystems/Trigger/WantedRPS", wantedRPS);
        Logger.recordOutput("Subsystems/Trigger/isOnTarget", isOnTarget());
    }

    public boolean isSensorSeeingRaw() {
        return triggerInputs.isSensorSeeing;
    }

    public boolean isSensorSeeing() {
        return isSensorSeeing;
    }

    @Override
    protected void setWantedSpeed(double dutyCycle) {
        this.wantedSpeed = dutyCycle;
    }

    @Override
    public boolean isOnTarget() {
        return isOnTarget(wantedRPS);
    }

    @Override
    public boolean isOnTarget(double value) {
        return isOnTargetDebouncer.calculate(Math.abs(value - getRPS()) <= TRIGGER_TOLERANCE);
    }

    @Override
    protected void setWantedRPS(double RPS) {
        this.wantedRPS = RPS;
    }

    @Override
    protected double getMaxRPS() {
        return 0;
    }

    @Override
    protected void moveByRPS() {
        return;
    }

    @Override
    protected List<OnyxMotorOutputs> getMotorInputs() {
        return List.of(triggerInputs.triggerMotorInputs);
    }

    @Override
    protected double getTotalCurrent() {
        return triggerInputs.triggerMotorInputs.getMotorSupplyCurrentAmps();
    }

    @Override
    protected void setWantedState(GlobalWantedState wantedState) {
        setWantedState(WantedState.valueOf(wantedState.toString()));
    }

    public double getRPS() {
        return triggerInputs.triggerMotorInputs.getMotorAngularVelocityRotPerSec();
    }

    public double getAcceleration() {
        return triggerInputs.triggerMotorInputs.getMotorAngularAccelerationRotPerSecSquared();
    }

    private static Trigger instance;

    public static void init(TriggerIO triggerIO) {
        if (instance == null) {
            instance = new Trigger(triggerIO);
        }
    }

    public static Trigger getInstance() {
        return instance;
    }
}
