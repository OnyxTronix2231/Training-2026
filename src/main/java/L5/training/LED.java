package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    public LED(int length) {
        this.length = length;
        this.buffer = new AddressableLEDBuffer(length);
        this.strip = new AddressableLEDSim();
        strip.setLength(buffer.getLength());
    }

    public void light (Color color, int index) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
        strip.setData(buffer);
    }
    public void lightAll (Color color) {
        for (int i = 0; i < this.length; i++) {
            buffer.setRGB(i, color.getRed(), color.getGreen(), color.getBlue());
        }
        strip.setData(buffer);
    }
}
