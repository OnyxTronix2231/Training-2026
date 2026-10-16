package frc.robot.subsystems.lecture.L6;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PhysicalTelemetryEntries;

import static frc.robot.subsystems.lecture.L6.TriggerConstants.*;

public class TriggerShuffleboard {
    public TriggerShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab(TRIGGER_SUBSYSTEM_NAME);
        Trigger trigger = Trigger.getInstance();

        new PhysicalTelemetryEntries(TRIGGER_SUBSYSTEM_NAME, TRIGGER_SUBSYSTEM_NAME,
            () -> trigger.getRPS(),
            () -> trigger.getAcceleration(),
            TRIGGER_DEFAULT_VELOCITY
        );

        tab.addString("Wanted State", () -> trigger.getWantedState().toString());
        tab.addString("System State", () -> trigger.getSystemState().toString());

        tab.addBoolean("Sensor Raw", () -> trigger.isSensorSeeingRaw());
        tab.addBoolean("Sensor Filtered", () -> trigger.isSensorSeeing());

        tab.addBoolean("isOnTarget", () -> trigger.isOnTarget());

        GenericEntry wantedDutyCycle = tab.add("Wanted Duty Cycle", TRIGGER_DEFAULT_DUTY_CYCLE).getEntry();

        tab.add("IDLE", new InstantCommand(() -> trigger.setWantedState(Trigger.WantedState.IDLE)));
        tab.add("MOVE_BY_SPEED", new InstantCommand(() -> trigger.setWantedState(Trigger.WantedState.MOVE_BY_SPEED,
            wantedDutyCycle.getDouble(TRIGGER_DEFAULT_DUTY_CYCLE))));
        tab.add("TEST", new InstantCommand(() -> trigger.setWantedState(Trigger.WantedState.TEST)));
    }
}
