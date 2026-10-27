package frc.robot.subsystems.Hinge;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.advancedSubsystem.VelocityBasedSubsystem;

import java.util.List;

import static frc.robot.subsystems.Hinge.HingeConstants.HINGE_MAX_RPS;

public class Hinge extends VelocityBasedSubsystem {

        private final HingeIO.HingeOutputs hingeOutputs;
        private final HingeIO hingeIO;

        public enum WantedState {
            IDLE,
            MOVE_BY_SPEED,
            TEST,
            DISABLED
        }

        public enum SystemState {
            IDLING,
            MOVING_BY_SPEED,
            TESTING,
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

        public Hinge(HingeIO hingeIO) {
            this.hingeOutputs = new HingeIO.HingeOutputs();
            this.hingeIO = hingeIO;
            this.hingeIO.updateOutputs(hingeOutputs);

            this.wantedState = WantedState.IDLE;
            this.systemState = Hinge.SystemState.IDLING;
            this.previousSystemState = SystemState.IDLING;
        }

        @Override
        public void periodic() {
            hingeIO.updateOutputs(hingeOutputs);

            systemState = handleStateTransition();
            applyStates();

            previousSystemState = systemState;
        }

        public SystemState handleStateTransition() {
            return switch (wantedState) {
                case IDLE -> SystemState.IDLING;
                case MOVE_BY_SPEED -> SystemState.MOVING_BY_SPEED;
                case TEST -> SystemState.TESTING;
                case DISABLED -> SystemState.DISABLED;
            };
        }

        public void applyStates() {
            switch (systemState) {
                case IDLING -> idleState();
                case MOVING_BY_SPEED -> moveBySpeed();
                case TESTING -> runTest();
                case DISABLED -> disabled();
            }
        }

        public void idleState() {
            hingeIO.setDutyCycle(0);
        }

        private double targetSpeed;

        public void setTargetSpeed(double targetSpeed) {
            this.targetSpeed = targetSpeed;
        }

        public void moveBySpeed() {
            hingeIO.setDutyCycle(targetSpeed);
        }

        @Override
        protected void setWantedSpeed(double dutyCycle) {
            this.targetSpeed = dutyCycle;
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
        }

        public double getAngle(){
            return hingeOutputs.hingeMotorOutputs.getValue();
        }

        public double getRPS() {
            return hingeOutputs.hingeMotorOutputs.getMotorAngularVelocityRotPerSec();
        }

        @Override
        protected double getMaxRPS() {
            return HINGE_MAX_RPS;

        }

        @Override
        protected void moveByRPS() {

        }

        @Override
        protected List<OnyxMotorOutputs> getMotorInputs() {
            return List.of(hingeOutputs.hingeMotorOutputs);

        }

        @Override
        protected double getTotalCurrent() {
            return hingeOutputs.hingeMotorOutputs.getMotorStatorCurrentAmps();
        }

        @Override
        protected void setWantedState(GlobalWantedState wantedState) {
            setWantedState(WantedState.valueOf(wantedState.toString()));
        }

        private void disabled() {
            if (previousSystemState != SystemState.DISABLED) {
                hingeIO.setDutyCycle(0);
            }
        }

        private static Hinge instance;

        public static void init(HingeIO hingeIO){
            if (instance == null){
                instance = new Hinge(hingeIO);
            }
        }
        public static Hinge getInstance(){
            return instance;
        }
    }
