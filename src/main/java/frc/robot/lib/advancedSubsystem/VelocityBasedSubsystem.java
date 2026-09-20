package frc.robot.lib.advancedSubsystem;

public abstract class VelocityBasedSubsystem extends AdvancedSubsystem {
    public VelocityBasedSubsystem() {
        super(false);
    }

    @Override
    protected double getForwardsSoftLimit() {
        return 0;
    }

    @Override
    protected double getBackwardsSoftLimit() {
        return 0;
    }

    @Override
    protected void moveToPosition() {
    }

    @Override
    protected void setWantedPosition(double position) {
    }
}
