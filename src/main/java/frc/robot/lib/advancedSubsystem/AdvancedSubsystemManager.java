package frc.robot.lib.advancedSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.subsystems.Superstructure;
import org.littletonrobotics.junction.Logger;

import java.util.List;

public class AdvancedSubsystemManager extends SubsystemBase {
    public static String globalMonitorPrefix = "subsystemStatus/";
    public static String globalPowerPrefix = "PowerMonitor/";
    public boolean shouldRun;
    private final List<AdvancedSubsystem> subsystems = AdvancedSubsystem.getSubsystems();

    /** Log keys, built once so new strings aren't created every cycle. */
    private final String[] powerCurrentKeys;
    private static final String SWERVE_CURRENT_KEY = globalPowerPrefix + "Swerve/Current";

    public AdvancedSubsystemManager() {
        shouldRun = false;

        powerCurrentKeys = new String[subsystems.size()];
        for (int i = 0; i < subsystems.size(); i++) {
            powerCurrentKeys[i] = globalPowerPrefix + subsystems.get(i).getName() + "/Current";
        }

        for (AdvancedSubsystem subsystem : subsystems) {
            String monitorPrefix = globalMonitorPrefix + subsystem.getName();
            String powerPrefix = globalPowerPrefix + subsystem.getName();

            SmartDashboard.putBoolean(monitorPrefix + "/testFinished", false);
            SmartDashboard.putBoolean(monitorPrefix + "/testResult", false);
            SmartDashboard.putBoolean(monitorPrefix + "/isPositionBased", subsystem.isPositionBased());

            if (subsystem.isPositionBased()) {
                SmartDashboard.putBoolean(monitorPrefix + "/forwardSoftLimitReached", subsystem.isPosForwardsSuccess());
                SmartDashboard.putBoolean(monitorPrefix + "/backwardSoftLimitReached", subsystem.isPosBackwardsSuccess());
            }

            SmartDashboard.putData(monitorPrefix + "/runTest", new InstantCommand(() -> {
                subsystem.setTestState(AdvancedSubsystem.TestState.START);
                subsystem.setWantedState(AdvancedSubsystem.GlobalWantedState.TEST);
            }));

            Logger.recordOutput(powerPrefix + "/Current", subsystem.getTotalCurrent());
            updateMotorTemp(monitorPrefix, subsystem);
        }

        String swervePrefix = globalMonitorPrefix + "swerve";

        SmartDashboard.putBoolean(swervePrefix + "/zeroedTestStatus", false);
        SmartDashboard.putBoolean(swervePrefix + "/forwardsTestStatus", false);
        SmartDashboard.putBoolean(swervePrefix + "/backwardsTestStatus", false);
    }


    @Override
    public void periodic() {
        if (shouldRun) {
            for (AdvancedSubsystem subsystem : subsystems) {
                String prefix = globalMonitorPrefix + subsystem.getName();

                SmartDashboard.putBoolean(prefix + "/testResult", subsystem.hasTestPassed());
                SmartDashboard.putBoolean(prefix + "/testFinished", subsystem.isTestCompleted());

                if (subsystem.isPositionBased()) {
                    SmartDashboard.putBoolean(prefix + "/forwardSoftLimitReached", subsystem.isPosForwardsSuccess());
                    SmartDashboard.putBoolean(prefix + "/backwardSoftLimitReached", subsystem.isPosBackwardsSuccess());
                }

                updateMotorTemp(prefix, subsystem);
            }

        }

        for (int i = 0; i < subsystems.size(); i++) {
            Logger.recordOutput(powerCurrentKeys[i], subsystems.get(i).getTotalCurrent());
        }

    }

    private void updateMotorTemp(String prefix, AdvancedSubsystem subsystem) {
        List<OnyxMotorOutputs> motors = subsystem.getMotorInputs();

        for (int i = 0; i < motors.size(); i++) {
            SmartDashboard.putNumber(prefix + "/motor-" + (i + 1) + "-temp", motors.get(i).getMotorTemperature());
        }
    }

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    /** Like {@link #setShouldRun(boolean)}, but does nothing if the manager wasn't created. */
    public static void setShouldRunIfPresent(boolean shouldRun) {
        if (instance != null) {
            instance.setShouldRun(shouldRun);
        }
    }

    private static AdvancedSubsystemManager instance;

    public static void init() {
        if (instance == null) {
            instance = new AdvancedSubsystemManager();
        }
    }

    public static AdvancedSubsystemManager getInstance() {
        return instance;
    }
}
