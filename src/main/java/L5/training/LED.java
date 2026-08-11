package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton b1;
    private KeyButton b2;

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }

    public enum WantedState {
        CW,
        CCW,
        IDLE
    }

    private SystemState systemState;
    private WantedState wantedState;

    public LED(int length) {
        this.length = length;
        this.buffer = new AddressableLEDBuffer(length);
        this.strip = new AddressableLEDSim();
        strip.setLength(buffer.getLength());

        b1 = new KeyButton(1);
        b2 = new KeyButton(2);

        systemState = systemState.OFF;
        wantedState = wantedState.IDLE;
    }

    private void placeInBuffer(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setOneLed(int index, Color color) {
        placeInBuffer(index, color);
        strip.setData(buffer);
    }

    public void lightOneLed (Color color, int index) {
        placeInBuffer(index, color);
        strip.setData(buffer);
    }
    public void lightAll (Color color) {
        for (int i = 0; i < this.length; i++) {
            placeInBuffer(i,color);
        }
        strip.setData(buffer);
    }

    public void paintRange(Color color, int index1, int index2) {
        if (index1 > index2) {
        for (int i = index2; i <= index1; i++) {
            placeInBuffer(i,color);
        }
        }else {
        for (int i = index1; i <= index2; i++) {
            placeInBuffer(i,color);
        }
        }
        strip.setData(buffer);
    }

    public void periodic() {

    }

    public void updateWantedState() {
        wantedState = WantedState.IDLE;
        if (b1.isPressed()) {
            wantedState = WantedState.CW;
        }
        if (b2.isPressed()) {
            wantedState = WantedState.CCW;
        }
    }
}
