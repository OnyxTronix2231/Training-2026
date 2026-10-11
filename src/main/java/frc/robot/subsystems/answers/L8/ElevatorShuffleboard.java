package frc.robot.subsystems.answers.L8;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;
import data.OnyxGenericValue;

import java.util.function.DoubleSupplier;

import static frc.robot.subsystems.answers.L8.ElevatorConstants.ELEVATOR_DEFAULT_POSITION;
import static frc.robot.subsystems.answers.L8.ElevatorConstants.ELEVATOR_SUBSYSTEM_NAME;
import static frc.robot.subsystems.answers.L8.ElevatorConstants.ElevatorRobotConstants.ROBOT_ELEVATOR_PID_VALUES;

public class ElevatorShuffleboard {
    public ElevatorShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab(ELEVATOR_SUBSYSTEM_NAME);

        PIDEntries elevatorEntries = new PIDEntries(ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_SUBSYSTEM_NAME, ROBOT_ELEVATOR_PID_VALUES);
        tab.add("Update elevator PID", new InstantCommand(() -> Elevator.getInstance().updatePID(elevatorEntries.getPIDValues())));

        PhysicalTelemetryEntries elevator = new PhysicalTelemetryEntries(ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_SUBSYSTEM_NAME,
            () -> Elevator.getInstance().getElevatorPosition(),
            () -> Elevator.getInstance().getElevatorVelocity(),
            () -> Elevator.getInstance().getElevatorAcceleration(),
            ELEVATOR_DEFAULT_POSITION
        );

        DoubleSupplier targetPosition = () -> elevator.getTargetPosition();
        OnyxGenericValue targetSpeed = new OnyxGenericValue("target speed", ELEVATOR_SUBSYSTEM_NAME);

        tab.addString("wanted state", () -> Elevator.getInstance().getWantedState().toString());
        tab.addString("system state", () -> Elevator.getInstance().getSystemState().toString());

        tab.add("idle", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.IDLE)));
        tab.add("move to position", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.MOVE_TO_POSITION, targetPosition.getAsDouble())));
        tab.add("move by speed", new InstantCommand(() -> Elevator.getInstance().setWantedStateWithSpeed(Elevator.WantedState.MOVE_BY_SPEED, targetSpeed.getDouble())));
    }
}
