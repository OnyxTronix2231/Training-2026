package frc.robot.lib;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.*;
import org.littletonrobotics.junction.Logger;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

public class OnyxMotorInputs {

    /** The physical CAN network used to group status signal refreshes. */
    public enum CanBus {
        RIO,
        CANIVORE
    }

    private final int motorID;
    private final StatusSignal<Temperature> motorTemperatureSignal;
    private final StatusSignal<AngularVelocity> motorAngularVelocityRotPerSecSignal;
    private final StatusSignal<AngularAcceleration> motorAngularAccelerationRotPerSecSquaredSignal;
    private final StatusSignal<Current> motorStatorCurrentAmpsSignal;
    private final StatusSignal<Voltage> motorAppliedVoltsSignal;
    private final StatusSignal<Current> motorSupplyCurrentAmpsSignal;
    private final StatusSignal<Angle> motorRawValueSignal;

    /**
     * Allocated exactly once, in the constructor. It reads the cached {@link #motorConvertedValue}
     * field rather than re-reading the signal, so it never needs to be reassigned.
     */
    private final DoubleSupplier motorValue;

    private final String motorName;
    private final DoubleUnaryOperator gearingFunction;
    private final String logBase;

    // Pre-built AdvantageKit keys. Concatenating these on every log() call cost 3 heap objects
    // per key per motor per cycle; building them once at construction costs nothing at runtime.
    private final String keyRawValue;
    private final String keyConvertedValue;
    private final String keyAppliedVolts;
    private final String keySupplyCurrentAmps;
    private final String keyStatorCurrentAmps;
    private final String keyAngularVelocity;
    private final String keyAngularAcceleration;

    private double motorTemperature;
    private double motorAngularVelocityRotPerSec;
    private double motorAngularAccelerationRotPerSecSquared;
    private double motorStatorCurrentAmps;
    private double motorAppliedVolts;
    private double motorSupplyCurrentAmps;
    private double motorRawValue;

    /** The gear-ratio-corrected position, computed once per cycle in {@link #updateInputs()}. */
    private double motorConvertedValue;

    private final boolean isActive;

    public OnyxMotorInputs(TalonFX motor, String subsystemName, String motorName, DoubleUnaryOperator gearingFunction, CanBus canBus) {
        isActive = true;

        this.gearingFunction = gearingFunction;
        motorID = motor.getDeviceID();
        motorTemperatureSignal = motor.getDeviceTemp();
        motorAngularVelocityRotPerSecSignal = motor.getVelocity();
        motorAngularAccelerationRotPerSecSquaredSignal = motor.getAcceleration();
        motorStatorCurrentAmpsSignal = motor.getStatorCurrent();
        motorAppliedVoltsSignal = motor.getMotorVoltage();
        motorSupplyCurrentAmpsSignal = motor.getSupplyCurrent();
        motorRawValueSignal = motor.getPosition();
        motorValue = () -> motorConvertedValue;
        logBase = "Subsystems/" + subsystemName + "/";
        this.motorName = motorName;

        String keyBase = logBase + motorName + "/";
        keyRawValue = keyBase + "RawValue";
        keyConvertedValue = keyBase + "ConvertedValue";
        keyAppliedVolts = keyBase + "AppliedVolts";
        keySupplyCurrentAmps = keyBase + "SupplyCurrentAmps";
        keyStatorCurrentAmps = keyBase + "StatorCurrentAmps";
        keyAngularVelocity = keyBase + "AngularVelocityRotPerSec";
        keyAngularAcceleration = keyBase + "AngularAccelerationRotPerSecSquared";

        SignalManager.register(canBus, motorTemperatureSignal, motorAngularVelocityRotPerSecSignal,
                motorAngularAccelerationRotPerSecSquaredSignal, motorStatorCurrentAmpsSignal,
                motorAppliedVoltsSignal, motorSupplyCurrentAmpsSignal, motorRawValueSignal);
    }

    public OnyxMotorInputs(TalonFX motor, String subsystemName, String motorName, CanBus canBus) {
        this(motor, subsystemName, motorName, angle -> angle, canBus);
    }

    /** Inactive inputs for simulated or absent motors; no CAN signals are registered. */
    public OnyxMotorInputs() {
        isActive = false;

        motorID = 0;
        motorTemperature = 0;
        motorAngularVelocityRotPerSec = 0;
        motorAngularAccelerationRotPerSecSquared = 0;
        motorStatorCurrentAmps = 0;
        motorAppliedVolts = 0;
        motorSupplyCurrentAmps = 0;
        motorRawValue = 0;
        motorConvertedValue = 0;

        motorTemperatureSignal = null;
        motorAngularVelocityRotPerSecSignal = null;
        motorAngularAccelerationRotPerSecSquaredSignal = null;
        motorStatorCurrentAmpsSignal = null;
        motorAppliedVoltsSignal = null;
        motorSupplyCurrentAmpsSignal = null;
        motorRawValueSignal = null;
        motorName = "";
        logBase = "";
        gearingFunction = angle -> angle;

        // Non-null so an inactive instance returns 0.0 instead of throwing.
        motorValue = () -> motorConvertedValue;

        keyRawValue = "";
        keyConvertedValue = "";
        keyAppliedVolts = "";
        keySupplyCurrentAmps = "";
        keyStatorCurrentAmps = "";
        keyAngularVelocity = "";
        keyAngularAcceleration = "";
    }

    public void updateInputs() {
        if (!isActive) return;
        motorTemperature = motorTemperatureSignal.getValueAsDouble();
        motorAngularVelocityRotPerSec = motorAngularVelocityRotPerSecSignal.getValueAsDouble();
        motorAngularAccelerationRotPerSecSquared = motorAngularAccelerationRotPerSecSquaredSignal.getValueAsDouble();
        motorStatorCurrentAmps = motorStatorCurrentAmpsSignal.getValueAsDouble();
        motorAppliedVolts = motorAppliedVoltsSignal.getValueAsDouble();
        motorSupplyCurrentAmps = motorSupplyCurrentAmpsSignal.getValueAsDouble();
        motorRawValue = motorRawValueSignal.getValueAsDouble();
        motorConvertedValue = gearingFunction.applyAsDouble(motorRawValue);
    }


    public void log() {
        if (!isActive) {
            return;
        }

        Logger.recordOutput(keyRawValue, motorRawValue);
        Logger.recordOutput(keyConvertedValue, motorConvertedValue);
        Logger.recordOutput(keyAppliedVolts, motorAppliedVolts);
        Logger.recordOutput(keySupplyCurrentAmps, motorSupplyCurrentAmps);
        Logger.recordOutput(keyStatorCurrentAmps, motorStatorCurrentAmps);
        Logger.recordOutput(keyAngularVelocity, motorAngularVelocityRotPerSec);
        Logger.recordOutput(keyAngularAcceleration, motorAngularAccelerationRotPerSecSquared);
    }

    public double getMotorAppliedVolts() {
        return motorAppliedVolts;
    }

    public double getMotorSupplyCurrentAmps() {
        return motorSupplyCurrentAmps;
    }

    public double getMotorStatorCurrentAmps() {
        return motorStatorCurrentAmps;
    }

    public double getMotorAngularVelocityRotPerSec() {
        return motorAngularVelocityRotPerSec;
    }

    public double getMotorAngularAccelerationRotPerSecSquared() {
        return motorAngularAccelerationRotPerSecSquared;
    }

    public double getMotorTemperature() {
        return motorTemperature;
    }

    public double getMotorRawValue() {
        return motorRawValue;
    }

    /**
     * Preferred accessor for the gear-ratio-corrected position: a plain field read, with no
     * supplier indirection and no signal re-read. Consistent for the whole loop cycle.
     */
    public double getValue() {
        return motorConvertedValue;
    }

    public int getMotorID() {
        return motorID;
    }

    public String getLogBase() {
        return logBase;
    }

    public String getMotorName() {
        return motorName;
    }

    /**
     * Kept for the Shuffleboard / {@code Logger.recordOutput(String, DoubleSupplier)} overloads
     * that require a supplier. The returned instance is allocated once and never replaced.
     */
    public DoubleSupplier getMotorValue() {
        return motorValue;
    }
}
