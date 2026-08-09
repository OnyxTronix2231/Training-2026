package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
   private int LEDLength;
   private AddressableLEDSim strip;
   private AddressableLEDBuffer buffer;
public  LED(int length) {
    this.LEDLength = length;
    strip = new AddressableLEDSim();
    buffer = new AddressableLEDBuffer(length);
    strip.setLength(buffer.getLength());
}
public void setled(int index, Color color) {
    
}
}
