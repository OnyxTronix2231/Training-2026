package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int LEDLength;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton b1;
    private KeyButton b2;
    private SystemState systemState;
    private WantedState wantedState;

    public LED(int length) {
        this.LEDLength = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        b1 = new KeyButton(1);
        b2 = new KeyButton(2);
        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;
    }

    public enum SystemState {
        RED,
        GREEN,
        BLUE,
        OFF
    }

    public enum WantedState {
        IDLE,
        CCW,
        CW,
    }

    public void updateWantedState() {
        wantedState = wantedState.IDLE;
        if (b1.isPressed()) {
            wantedState = WantedState.CW;
        }
        if (b2.isPressed()) {
            wantedState = WantedState.CCW;
        }

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

    public void periodic() {

    }
}

