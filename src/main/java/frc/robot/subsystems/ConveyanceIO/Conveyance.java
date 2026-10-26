package frc.robot.subsystems.ConveyanceIO;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyance extends SubsystemBase {
    private final ConveyanceIO.ConveyanceOutputs conveyanceOutputs;
    private final ConveyanceIO conveyanceIO;

        public enum WantedState {
            IDLE,
            MOVE_BY_SPEED,
            DISABLE
        }

        public enum SystemState {
            IDLING,
            MOVING_BY_SPEED,
            DISABLED
        }

        private WantedState wantedState;
        private SystemState systemState;
        private SystemState previousSystemState;

        public void setWantedState(WantedState wantedState) {
            this.wantedState = wantedState;
        }
        public WantedState getWantedState() {
            return wantedState;
        }
        public SystemState getSystemState() {
            return systemState;
        }

        public Conveyance(ConveyanceIO conveyanceIO) {
            this.conveyanceOutputs = new ConveyanceIO.ConveyanceOutputs();
            this.conveyanceIO = conveyanceIO;
            this.conveyanceIO.updateOutputs(conveyanceOutputs);

            this.wantedState = WantedState.IDLE;
            this.systemState = SystemState.IDLING;
            this.previousSystemState = SystemState.IDLING;
        }

        @Override
        public void periodic() {
            conveyanceIO.updateOutputs(conveyanceOutputs);

            systemState = handleStateTransition();
            applyStates();

            previousSystemState = systemState;
        }

        public SystemState handleStateTransition() {
            return switch (wantedState) {
                case IDLE -> SystemState.IDLING;
                case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
                case DISABLE -> SystemState.DISABLED;
            };
        }

        public void applyStates() {
            switch (systemState) {
                case IDLING -> idleState();
                case MOVING_BY_SPEED -> moveBySpeed();
                case DISABLED -> disabled();
            }
        }

        public void idleState() {
            conveyanceIO.setDutyCycle(0);
        }

        private double targetSpeed;
        public void setTargetSpeed(double targetSpeed) {
            this.targetSpeed = targetSpeed;
        }
        public void moveBySpeed() {
            conveyanceIO.setDutyCycle(targetSpeed);
        }

        private void disabled() {
            if (previousSystemState != SystemState.DISABLED) {
                conveyanceIO.setDutyCycle(0);
                conveyanceIO.setNeutralMode(NeutralModeValue.Coast);
            }
        }

    }
