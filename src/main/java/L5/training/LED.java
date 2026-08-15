package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

   public LED(int length){
       this.length = length;
       this.buffer = new AddressableLEDBuffer(length);
       this.strip = new AddressableLEDSim();
       strip.setLength(buffer.getLength());
   }

    public void powerLed(Color ledColor, int index){
        buffer.setRGB(index, ledColor.getRed(), ledColor.getGreen(), ledColor.getBlue());
        strip.setData(buffer);
    }
}
