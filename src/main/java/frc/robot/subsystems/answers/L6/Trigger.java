package frc.robot.subsystems.answers.L6;

import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;
import org.littletonrobotics.junction.Logger;

import java.util.List;

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

    private double wantedSpeed;

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

    public double getWantedSpeed() {
        return wantedSpeed;
    }

    public Trigger(TriggerIO triggerIO) {
        this.triggerInputs = new TriggerIO.TriggerInputs();
        this.triggerIO = triggerIO;

        this.triggerIO.updateInputs(triggerInputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;

        this.wantedSpeed = 0;
    }

    @Override
    public void periodic() {
        triggerIO.updateInputs(triggerInputs);

        systemState = handleStateTransition();

        applyStates();

        log();
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

        Logger.recordOutput("Subsystems/Turret/WantedState", wantedState);
        Logger.recordOutput("Subsystems/Turret/SystemState", systemState);

        Logger.recordOutput("Subsystems/Turret/isSensorSeeing", triggerInputs.isSensorSeeing);
    }

    @Override
    protected void setWantedSpeed(double dutyCycle) {
        this.wantedSpeed = dutyCycle;
    }

    @Override
    public boolean isOnTarget() {
        return false;
    }

    @Override
    public boolean isOnTarget(double value) {
        return false;
    }

    @Override
    protected void setWantedRPS(double RPS) {
        return;
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
    protected List<OnyxMotorInputs> getMotorInputs() {
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

    public double getAcceleration() {
        return triggerInputs.triggerMotorInputs.getMotorAngularAccelerationRotPerSecSquared();
    }

    public boolean getSensorValue() {
        return triggerInputs.isSensorSeeing;
    }

    public double getRPS() {
        return triggerInputs.triggerMotorInputs.getMotorAngularVelocityRotPerSec();
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
