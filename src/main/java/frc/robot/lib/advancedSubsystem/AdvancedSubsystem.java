package frc.robot.lib.advancedSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.OnyxMotorInputs;

import java.util.ArrayList;
import java.util.List;

import static frc.robot.lib.advancedSubsystem.AdvancedSubsystemConstants.*;

public abstract class AdvancedSubsystem extends SubsystemBase {
    private static final List<AdvancedSubsystem> subsystems = new ArrayList<>();
    private final boolean isPositionBased;
    private boolean testCompleted;
    private final double positionTestSpeed;
    private final double pidPosition;

    public enum GlobalWantedState {
        IDLE,
        TEST
    }

    public static List<AdvancedSubsystem> getSubsystems() {
        return subsystems;
    }

    public AdvancedSubsystem(boolean isPositionBased) {
        testState = TestState.START;
        previousTestState = TestState.START;

        testStart = -1;
        reachedTargetStart = -1;

        velocityTestSuccess = false;
        posForwardsSuccess = false;
        posBackwardsSuccess = false;
        posPIDSuccess = false;

        subsystems.add(this);
        testCompleted = false;

        this.isPositionBased = isPositionBased;
        this.positionTestSpeed = POSITION_TEST_SPEED;

        double backwards = getBackwardsSoftLimit();
        double forwards = getForwardsSoftLimit();
        pidPosition = backwards + (POSITION_PID_TARGET_PERCENT / 100.0) * (forwards - backwards);
    }

    public AdvancedSubsystem(boolean isPositionBased, double pidPosition) {
        testState = TestState.START;
        previousTestState = TestState.START;

        testStart = -1;
        reachedTargetStart = -1;

        velocityTestSuccess = false;
        posForwardsSuccess = false;
        posBackwardsSuccess = false;
        posPIDSuccess = false;

        subsystems.add(this);
        testCompleted = false;

        this.isPositionBased = isPositionBased;
        this.positionTestSpeed = POSITION_TEST_SPEED;
        this.pidPosition = pidPosition;
    }

    public AdvancedSubsystem(boolean isPositionBased, double positionTestSpeed, double pidPosition) {
        testState = TestState.START;
        previousTestState = TestState.START;

        testStart = -1;
        reachedTargetStart = -1;

        velocityTestSuccess = false;
        posForwardsSuccess = false;
        posBackwardsSuccess = false;
        posPIDSuccess = false;

        subsystems.add(this);
        testCompleted = false;

        this.isPositionBased = isPositionBased;
        this.positionTestSpeed = positionTestSpeed;
        this.pidPosition = pidPosition;
    }

    protected enum TestState {
        START,
        VELOCITY_TEST,
        POS_FORWARDS,
        POS_BACKWARDS,
        POS_PID,
        COMPLETED,
        FAILED
    }

    private TestState testState;
    private TestState previousTestState;

    private double testStart;
    private double reachedTargetStart;

    private boolean velocityTestSuccess;
    private boolean posForwardsSuccess;
    private boolean posBackwardsSuccess;
    private boolean posPIDSuccess;

    private void moveByRPS(double RPS) {
        setWantedRPS(RPS);
        moveByRPS();
    }

    private void moveBySpeed(double dutyCycle) {
        setWantedSpeed(dutyCycle);
        moveBySpeed();
    }

    private void moveToPosition(double position) {
        setWantedPosition(position);
        moveToPosition();
    }

    protected void runTest() {
        double now = Timer.getFPGATimestamp();
        TestState stateAtRunStart = testState;

        TestState tempState = testState;

        switch (stateAtRunStart) {
            case START -> {
                idleState();
                if (isPositionBased()) {
                    testState = TestState.POS_FORWARDS;
                } else {
                    reachedTargetStart = -1;
                    testState = TestState.VELOCITY_TEST;
                }

                reachedTargetStart = -1;

                velocityTestSuccess = false;
                posForwardsSuccess = false;
                posBackwardsSuccess = false;
                posPIDSuccess = false;
                testCompleted = false;
            }
            case VELOCITY_TEST -> {
                if (testState != previousTestState) {
                    testStart = now;
                }
                moveByRPS(getMaxRPS());
                if (isOnTarget()) {
                    if (reachedTargetStart == -1) {
                        reachedTargetStart = now;
                    } else if (now - reachedTargetStart >= VELOCITY_TEST_SUCCESS_TIME_SECONDS) {
                        velocityTestSuccess = true;
                    }
                } else if (reachedTargetStart != -1) {
                    reachedTargetStart = -1;
                }
                if (now - testStart >= VELOCITY_TEST_TIME_SECONDS || velocityTestSuccess) {
                    if (velocityTestSuccess) {
                        testState = TestState.COMPLETED;
                    } else {
                        testState = TestState.FAILED;
                    }
                }
            }
            case POS_FORWARDS -> {
                if (testState != previousTestState) {
                    testStart = now;
                }
                moveBySpeed(positionTestSpeed);

                if (isOnTarget(getForwardsSoftLimit())) {
                    posForwardsSuccess = true;
                    testState = TestState.POS_BACKWARDS;
                } else if (now - testStart >= POSITION_TEST_TIME_SECONDS) {
                    posForwardsSuccess = false;
                    testState = TestState.FAILED;
                }
            }
            case POS_BACKWARDS -> {
                if (testState != previousTestState) {
                    testStart = now;
                }
                moveBySpeed(-positionTestSpeed);
                if (isOnTarget(getBackwardsSoftLimit())) {
                    posBackwardsSuccess = true;
                    testState = TestState.POS_PID;
                } else if (now - testStart >= POSITION_TEST_TIME_SECONDS) {
                    posBackwardsSuccess = false;
                    testState = TestState.FAILED;
                }
            }
            case POS_PID -> {
                if (testState != previousTestState) {
                    testStart = now;
                }
                moveToPosition(pidPosition);

                if (isOnTarget()) {
                    posPIDSuccess = true;
                    testState = TestState.COMPLETED;
                } else if (now - testStart >= POSITION_TEST_TIME_SECONDS) {
                    posPIDSuccess = false;
                    testState = TestState.FAILED;
                }
            }
            case COMPLETED, FAILED -> {
                testCompleted = true;
                idleState();
            }
        }

        previousTestState = tempState;
    }

    public final boolean isTestCompleted() {
        return testCompleted;
    }

    public final boolean isVelocityTestSuccess() {
        return velocityTestSuccess;
    }

    public final boolean isPosForwardsSuccess() {
        return posForwardsSuccess;
    }

    public final boolean isPosBackwardsSuccess() {
        return posBackwardsSuccess;
    }

    public final boolean isPosPIDSuccess() {
        return posPIDSuccess;
    }

    public final boolean hasTestPassed() {
        if (isPositionBased()) {
            return isPosBackwardsSuccess() && isPosForwardsSuccess() && isPosPIDSuccess();
        } else {
            return isVelocityTestSuccess();
        }
    }

    public final boolean isPositionBased() {
        return isPositionBased;
    }

    protected final void setTestState(TestState testState) {
        this.testState = testState;
    }

    protected abstract void idleState();

    protected abstract void moveBySpeed();

    protected abstract void setWantedSpeed(double dutyCycle);

    public abstract boolean isOnTarget();

    public abstract boolean isOnTarget(double value);

    protected abstract void setWantedRPS(double RPS);

    protected abstract double getMaxRPS();

    protected abstract void moveByRPS();

    protected abstract void setWantedPosition(double position);

    protected abstract double getForwardsSoftLimit();

    protected abstract double getBackwardsSoftLimit();

    protected abstract void moveToPosition();

    protected abstract List<OnyxMotorInputs> getMotorInputs();

    protected abstract double getTotalCurrent();

    protected abstract void setWantedState(GlobalWantedState wantedState);
}
