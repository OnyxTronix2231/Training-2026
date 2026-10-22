package frc.robot.lib;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.*;
import org.littletonrobotics.junction.Logger;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

public class OnyxMotorOutputs {

    public enum CanBus {
        RIO,
        CANIVORE
    }

    // Status signal update rates (Hz). The robot loop runs at 50 Hz.
    private static final double POSITION_VELOCITY_SIGNAL_HZ = 50; // every loop
    private static final double OTHER_SIGNAL_HZ = 10; // every 5 loops

    private final boolean isActive;
    private final int motorID;
    private final String motorName;
    private final String logBase;
    private final DoubleUnaryOperator gearingFunction;
    private final DoubleSupplier motorValue = () -> this.convertedValue;

    private final StatusSignal<Temperature> temperatureSignal;
    private final StatusSignal<AngularVelocity> angularVelocityRotPerSecSignal;
    private final StatusSignal<AngularAcceleration> angularAccelerationRotPerSecSquaredSignal;
    private final StatusSignal<Current> statorCurrentAmpsSignal;
    private final StatusSignal<Voltage> appliedVoltsSignal;
    private final StatusSignal<Current> supplyCurrentAmpsSignal;
    private final StatusSignal<Angle> rawValueSignal;

    // Log keys are built once so log() doesn't create new strings every cycle
    private final String keyRawValue;
    private final String keyConvertedValue;
    private final String keyAppliedVolts;
    private final String keySupplyCurrentAmps;
    private final String keyStatorCurrentAmps;
    private final String keyAngularVelocity;
    private final String keyAngularAcceleration;

    private double temperature;
    private double angularVelocityRotPerSec;
    private double angularAccelerationRotPerSecSquared;
    private double statorCurrentAmps;
    private double appliedVolts;
    private double supplyCurrentAmps;
    private double rawValue;
    private double convertedValue;

    public OnyxMotorOutputs(TalonFX motor, String subsystemName, String motorName,
                            DoubleUnaryOperator gearingFunction, CanBus canBus) {
        isActive = true;
        motorID = motor.getDeviceID();
        this.motorName = motorName;
        this.gearingFunction = gearingFunction;
        logBase = "Subsystems/" + subsystemName + "/";

        temperatureSignal = motor.getDeviceTemp();
        angularVelocityRotPerSecSignal = motor.getVelocity();
        angularAccelerationRotPerSecSquaredSignal = motor.getAcceleration();
        statorCurrentAmpsSignal = motor.getStatorCurrent();
        appliedVoltsSignal = motor.getMotorVoltage();
        supplyCurrentAmpsSignal = motor.getSupplyCurrent();
        rawValueSignal = motor.getPosition();

        String keyBase = logBase + motorName + "/";
        keyRawValue = keyBase + "RawValue";
        keyConvertedValue = keyBase + "ConvertedValue";
        keyAppliedVolts = keyBase + "AppliedVolts";
        keySupplyCurrentAmps = keyBase + "SupplyCurrentAmps";
        keyStatorCurrentAmps = keyBase + "StatorCurrentAmps";
        keyAngularVelocity = keyBase + "AngularVelocityRotPerSec";
        keyAngularAcceleration = keyBase + "AngularAccelerationRotPerSecSquared";

        SignalManager.register(canBus, temperatureSignal, angularVelocityRotPerSecSignal, angularAccelerationRotPerSecSquaredSignal,
                statorCurrentAmpsSignal, appliedVoltsSignal, supplyCurrentAmpsSignal, rawValueSignal);

        tuneBusUtilization();
    }

    public OnyxMotorOutputs(TalonFX motor, String subsystemName, String motorName, CanBus canBus) {
        this(motor, subsystemName, motorName, angle -> angle, canBus);
    }

    /** Inactive outputs for simulated or absent motors. All values read as 0. */
    public OnyxMotorOutputs() {
        isActive = false;
        motorID = 0;
        motorName = "";
        logBase = "";
        gearingFunction = angle -> angle;

        temperatureSignal = null;
        angularVelocityRotPerSecSignal = null;
        angularAccelerationRotPerSecSquaredSignal = null;
        statorCurrentAmpsSignal = null;
        appliedVoltsSignal = null;
        supplyCurrentAmpsSignal = null;
        rawValueSignal = null;

        keyRawValue = keyConvertedValue = keyAppliedVolts = keySupplyCurrentAmps =
                keyStatorCurrentAmps = keyAngularVelocity = keyAngularAcceleration = "";
    }

    /** Sets the update rates of the signals above. All other signals keep their default rates. */
    private void tuneBusUtilization() {
        // Applied voltage is also what follower motors copy, so it must update every loop
        BaseStatusSignal.setUpdateFrequencyForAll(POSITION_VELOCITY_SIGNAL_HZ,
                rawValueSignal, angularVelocityRotPerSecSignal, appliedVoltsSignal);
        BaseStatusSignal.setUpdateFrequencyForAll(OTHER_SIGNAL_HZ,
                statorCurrentAmpsSignal, angularAccelerationRotPerSecSquaredSignal,
                supplyCurrentAmpsSignal, temperatureSignal);
    }

    public void updateOutputs() {
        if (!isActive) return;

        temperature = temperatureSignal.getValueAsDouble();
        angularVelocityRotPerSec = angularVelocityRotPerSecSignal.getValueAsDouble();
        angularAccelerationRotPerSecSquared = angularAccelerationRotPerSecSquaredSignal.getValueAsDouble();
        statorCurrentAmps = statorCurrentAmpsSignal.getValueAsDouble();
        appliedVolts = appliedVoltsSignal.getValueAsDouble();
        supplyCurrentAmps = supplyCurrentAmpsSignal.getValueAsDouble();
        rawValue = rawValueSignal.getValueAsDouble();
        convertedValue = gearingFunction.applyAsDouble(rawValue);
    }

    public void log() {
        if (!isActive) return;

        Logger.recordOutput(keyRawValue, rawValue);
        Logger.recordOutput(keyConvertedValue, convertedValue);
        Logger.recordOutput(keyAppliedVolts, appliedVolts);
        Logger.recordOutput(keySupplyCurrentAmps, supplyCurrentAmps);
        Logger.recordOutput(keyStatorCurrentAmps, statorCurrentAmps);
        Logger.recordOutput(keyAngularVelocity, angularVelocityRotPerSec);
        Logger.recordOutput(keyAngularAcceleration, angularAccelerationRotPerSecSquared);
    }

    public double getMotorAppliedVolts() {
        return appliedVolts;
    }

    public double getMotorSupplyCurrentAmps() {
        return supplyCurrentAmps;
    }

    public double getMotorStatorCurrentAmps() {
        return statorCurrentAmps;
    }

    public double getMotorAngularVelocityRotPerSec() {
        return angularVelocityRotPerSec;
    }

    public double getMotorAngularAccelerationRotPerSecSquared() {
        return angularAccelerationRotPerSecSquared;
    }

    public double getMotorTemperature() {
        return temperature;
    }

    public double getMotorRawValue() {
        return rawValue;
    }

    /** Position after the gearing function is applied. */
    public double getValue() {
        return convertedValue;
    }

    public DoubleSupplier getMotorValue() {
        return motorValue;
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
}
