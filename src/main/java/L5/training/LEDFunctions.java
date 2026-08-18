package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import java.awt.*;

public class LEDFunctions {
    private int LEDLength;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;


    public LEDFunctions(int length) {
        this.LEDLength = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
    }

    private void placeinbuffer(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void Setled(int index, Color color) {
        placeinbuffer(index, color);
        strip.setData(buffer);


    }

    public void fullcolour(Color colour) {
        for (int i = 0; i < LEDLength; i++) {
            placeinbuffer(i, colour);
            strip.setData(buffer);
        }
    }

    public void colourBetween(int index, int indexEnd, Color colour) {
        for (int i = index; i < indexEnd + 1; i++) {
            placeinbuffer(i, colour);
            strip.setData(buffer);
        }
    }
}


