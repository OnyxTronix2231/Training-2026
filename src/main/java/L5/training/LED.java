package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int Length;
    private AddressableLEDSim addressableLEDSim;
    private AddressableLEDBuffer Buffer;
    public LED(int Length){
        this.Length=Length;
        addressableLEDSim = new AddressableLEDSim();
        Buffer= new AddressableLEDBuffer(Length);
        addressableLEDSim.setLength(Buffer.getLength());

    }
    public void Color(int index, Color color){
        Buffer.setRGB(index,color.getRed(),color.getGreen(),color.getBlue());
        addressableLEDSim.setData(Buffer);


    }




}
