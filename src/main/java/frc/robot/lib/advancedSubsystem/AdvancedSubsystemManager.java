package frc.robot.lib.advancedSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.LimelightHelpers;
//import frc.robot.lib.OnyxMotorInputs;
//import frc.robot.subsystems.Superstructure;
//import frc.robot.subsystems.localization.Localization;
//import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;
//import org.littletonrobotics.junction.Logger;

import java.util.List;

public class AdvancedSubsystemManager extends SubsystemBase {
//    public static String globalMonitorPrefix = "subsystemStatus/";
//    public static String globalPowerPrefix = "PowerMonitor/";
//    public boolean shouldRun;
//    private final List<AdvancedSubsystem> subsystems = AdvancedSubsystem.getSubsystems();
//
//    public AdvancedSubsystemManager() {
//        shouldRun = false;
//
//        for (AdvancedSubsystem subsystem : subsystems) {
//            String monitorPrefix = globalMonitorPrefix + subsystem.getName();
//            String powerPrefix = globalPowerPrefix + subsystem.getName();
//
//            SmartDashboard.putBoolean(monitorPrefix + "/testFinished", false);
//            SmartDashboard.putBoolean(monitorPrefix + "/testResult", false);
//            SmartDashboard.putBoolean(monitorPrefix + "/isPositionBased", subsystem.isPositionBased());
//
//            if (subsystem.isPositionBased()) {
//                SmartDashboard.putBoolean(monitorPrefix + "/forwardSoftLimitReached", subsystem.isPosForwardsSuccess());
//                SmartDashboard.putBoolean(monitorPrefix + "/backwardSoftLimitReached", subsystem.isPosBackwardsSuccess());
//            }
//
//            SmartDashboard.putData(monitorPrefix + "/runTest", new InstantCommand(() -> {
//                subsystem.setTestState(AdvancedSubsystem.TestState.START);
//                subsystem.setWantedState(AdvancedSubsystem.GlobalWantedState.TEST);
//            }));
//
//            Logger.recordOutput(powerPrefix + "/Current", subsystem.getTotalCurrent());
//            updateMotorTemp(monitorPrefix, subsystem);
//        }
//
//        String swervePrefix = globalMonitorPrefix + "swerve";
//
//        SmartDashboard.putBoolean(swervePrefix + "/zeroedTestStatus", false);
//        SmartDashboard.putBoolean(swervePrefix + "/forwardsTestStatus", false);
//        SmartDashboard.putBoolean(swervePrefix + "/backwardsTestStatus", false);
//
//        SmartDashboard.putData(swervePrefix + "/allowContinuing", new InstantCommand(() -> {
//            CommandSwerveDrivetrain.getInstance().setCanContinueTest(true);
//        }));
//
//        SmartDashboard.putData(swervePrefix + "/runTest", new InstantCommand(() -> {
//            CommandSwerveDrivetrain.getInstance().setWantedState(CommandSwerveDrivetrain.WantedState.TEST);
//            CommandSwerveDrivetrain.getInstance().setTestState(CommandSwerveDrivetrain.TestState.START);
//        }));
//
//        Logger.recordOutput(globalPowerPrefix + "Swerve/Current", CommandSwerveDrivetrain.getInstance().getAvgSupplyCurrent()*4);
//
//        SmartDashboard.putData(globalMonitorPrefix + "stopAllSubsystems", new InstantCommand(() -> {
//            for (AdvancedSubsystem subsystem : subsystems) {
//                subsystem.setWantedState(AdvancedSubsystem.GlobalWantedState.IDLE);
//            }
//            Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.TEST);
//        }));
//
//        SmartDashboard.putData(globalMonitorPrefix + "testAllSubsystems", new InstantCommand(() -> Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.PIT_SHOOT)));
//
//        var services = Localization.getInstance().getLocalizationServices();
//        for (var service : services) {
//            String name = service.getName();
//            SmartDashboard.putBoolean("limelight/" + name + "/isAlive", false);
//            SmartDashboard.putNumber("limelight/" + name + "/temp", 0);
//        }
//    }
//
//
//    @Override
//    public void periodic() {
//        if (shouldRun) {
//            var services = Localization.getInstance().getLocalizationServices();
//            for (var service : services) {
//                String name = service.getName();
//                SmartDashboard.putBoolean("limelight/" + name + "/isAlive", LimelightHelpers.getLatency_Pipeline(name) > 0);
//
//                double temp = -1;
//                double[] hw = LimelightHelpers.getLimelightNTDoubleArray(name, "hw");
//                if (hw != null && hw.length > 0) {
//                    temp = hw[0];
//                }
//                SmartDashboard.putNumber("limelight/" + name + "/temp", temp);
//            }
//
//            for (AdvancedSubsystem subsystem : subsystems) {
//                String prefix = globalMonitorPrefix + subsystem.getName();
//
//                SmartDashboard.putBoolean(prefix + "/testResult", subsystem.hasTestPassed());
//                SmartDashboard.putBoolean(prefix + "/testFinished", subsystem.isTestCompleted());
//
//                if (subsystem.isPositionBased()) {
//                    SmartDashboard.putBoolean(prefix + "/forwardSoftLimitReached", subsystem.isPosForwardsSuccess());
//                    SmartDashboard.putBoolean(prefix + "/backwardSoftLimitReached", subsystem.isPosBackwardsSuccess());
//                }
//
//                updateMotorTemp(prefix, subsystem);
//            }
//
//            String swervePrefix = globalMonitorPrefix + "swerve";
//            SmartDashboard.putBoolean(swervePrefix + "/zeroedTestStatus", CommandSwerveDrivetrain.getInstance().isZeroedTestSuccess());
//            SmartDashboard.putBoolean(swervePrefix + "/forwardsTestStatus", CommandSwerveDrivetrain.getInstance().isForwardsTestSuccess());
//            SmartDashboard.putBoolean(swervePrefix + "/backwardsTestStatus", CommandSwerveDrivetrain.getInstance().isBackwardsTestSuccess());
//            SmartDashboard.putBoolean(swervePrefix + "/testCompleted", CommandSwerveDrivetrain.getInstance().isTestCompleted());
//        }
//
//        for (AdvancedSubsystem subsystem : subsystems) {
//            String prefix = globalPowerPrefix + subsystem.getName();
//            Logger.recordOutput(prefix + "/Current", subsystem.getTotalCurrent());
//        }
//        Logger.recordOutput(globalPowerPrefix + "Swerve/Current", CommandSwerveDrivetrain.getInstance().getAvgSupplyCurrent());
//    }
//
//    private void updateMotorTemp(String prefix, AdvancedSubsystem subsystem) {
//        List<OnyxMotorInputs> motors = subsystem.getMotorInputs();
//
//        for (int i = 0; i < motors.size(); i++) {
//            SmartDashboard.putNumber(prefix + "/motor-" + (i + 1) + "-temp", motors.get(i).getMotorTemperature());
//        }
//    }
//
//    public void setShouldRun(boolean shouldRun) {
//        this.shouldRun = shouldRun;
//    }
//
//    private static AdvancedSubsystemManager instance;
//
//    public static void init() {
//        if (instance == null) {
//            instance = new AdvancedSubsystemManager();
//        }
//    }
//
//    public static AdvancedSubsystemManager getInstance() {
//        return instance;
//    }
}
