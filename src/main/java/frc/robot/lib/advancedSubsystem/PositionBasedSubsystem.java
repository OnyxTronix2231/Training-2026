package frc.robot.lib.advancedSubsystem;

public abstract class PositionBasedSubsystem extends AdvancedSubsystem {
    public PositionBasedSubsystem(double pidPosition) {
        super(true, pidPosition);
    }

    public PositionBasedSubsystem(double pidPosition, double positionTestSpeed) {
        super(true, positionTestSpeed, pidPosition);
    }

    @Override
    protected double getMaxRPS() {
        return 0;
    }

    @Override
    protected void setWantedRPS(double velocity) {
    }

    @Override
    protected void moveByRPS() {
    }
}
