package frc.robot.subsystems.Conveyance;

public class Conveyance {

    private final ConveyanceIO.Conveyanceoutputs conveyanceOutputs;
    private final ConveyanceIO conveyanceIO;

    public Conveyance(ConveyanceIO conveyanceIO) {
        this.conveyanceIO = conveyanceIO;
        this.conveyanceOutputs = new ConveyanceIO.Conveyanceoutputs();
    }

    public ConveyanceIO getConveyanceIO() {
        return conveyanceIO;
    }

    public void periodic() {
        conveyanceIO.updateOutputs(conveyanceOutputs);
    }
}