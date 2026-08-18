package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;



public class LED {

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    public LED(int lengt) {
        this.length = lengt;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
    }

    private void placeInBuffer(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setOneLed(Color color, int index) {
        placeInBuffer(index, color);
        strip.setData(buffer);
    }

    public void setColor(Color c) {
        for (int i = 0; i < length; i++) {
            placeInBuffer(i, c);
        }
        strip.setData(buffer);
    }

    public void fullCOlor(Color a, int index1, int index2) {
        for (int i = index1; i < index2; i++) {
            placeInBuffer(i, a);
        }
        strip.setData(buffer);
    }





}
