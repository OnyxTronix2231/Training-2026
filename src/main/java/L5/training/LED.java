package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
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

    private void bufferSet(int index, Color color){
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void powerOneLed(Color color, int index) {
        bufferSet(index, color);
        strip.setData(buffer);
    }

    public void powerAllLed(Color color){
        for (int i = 0; i < buffer.getLength(); i++) {
            bufferSet(i, color);
        }
        strip.setData(buffer);
    }

    public void range(Color color, int start, int end){
        for (int i = start; i <= end; i++) {
            bufferSet(i, color);
        }
        strip.setData(buffer);
    }
}
