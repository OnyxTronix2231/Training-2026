package frc.robot.subsystems.answers.L6;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;

import java.util.function.DoubleSupplier;

import static frc.robot.subsystems.answers.L6.TriggerConstants.*;

public class TriggerShuffleboard {
    public TriggerShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab(TRIGGER_SUBSYSTEM_NAME);

        PhysicalTelemetryEntries trigger = new PhysicalTelemetryEntries(TRIGGER_SUBSYSTEM_NAME, TRIGGER_SUBSYSTEM_NAME,
            () -> Trigger.getInstance().getRPS(),
            () -> Trigger.getInstance().getAcceleration(),
            TRIGGER_DEFAULT_VELOCITY
        );

        tab.addString("Wanted State", () -> Trigger.getInstance().getWantedState().toString());
        tab.addString("System State", () -> Trigger.getInstance().getSystemState().toString());

        tab.add("Idle", new InstantCommand(() -> Trigger.getInstance().setWantedState(Trigger.WantedState.IDLE)));
        tab.add("Move by Speed", new InstantCommand(() -> Trigger.getInstance().setWantedState(Trigger.WantedState.MOVE_BY_SPEED , trigger.getTargetVelocity()))));

        tab.addBoolean("Trigger on Target", () -> Trigger.getInstance().isOnTarget());

        tab.addBoolean("Sensor Value", () -> Trigger.getInstance().getSensorValue());

        tab.add("Toggle Sensor", new InstantCommand((() -> Trigger.getInstance().))
    }
}
