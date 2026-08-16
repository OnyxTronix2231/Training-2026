package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

import static L5.training.LED.WantedState.BLUE;
import static L5.training.LED.WantedState.IDLE;


public class LED {

    private int length;
    private AddressableLEDSim strip;
    ;
    private AddressableLEDBuffer buffer;
    private KeyButton B1;
    private KeyButton B2;
    private KeyButton B3;

    public LED(int lengt) {
        this.length = lengt;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        B1 = new KeyButton(1);
        B2 = new KeyButton(2);
        B3 = new KeyButton(3);
        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;
    }

    private void placeInBuffer(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void color123(Color color, int index, int index1) {
        placeInBuffer(index, color);
        placeInBuffer(index1, color);
        strip.setData(buffer);
    }

    public void color124(Color c) {
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

    public enum WantedState {
        RED,
        BLUE,
        GREEN,
        IDLE,
    }

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }

    private SystemState systemState;
    private WantedState wantedState;

    public void periodic() {
        updateWantedState();
        systemState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        wantedState = IDLE;
        if (B1.isPressed()) {
            wantedState = BLUE;
        }
        if (B2.isPressed()) {
            wantedState = WantedState.RED;
        }
        if (B3.isPressed()) {
            wantedState = WantedState.GREEN;
        }
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case BLUE:
                switch (systemState) {
                    case OFF:
                        return SystemState.BLUE;
                    case BLUE:
                        return SystemState.OFF;
                }
                break;
            case GREEN:
                switch (systemState) {
                    case OFF:
                        return SystemState.GREEN;
                    case GREEN:
                        return SystemState.OFF;

                }
            case RED:
                switch (systemState) {
                    case OFF:
                        return SystemState.RED;
                    case RED:
                        return SystemState.OFF;

                }
        }
        return systemState;
    }

    public void applyState() {
        switch (systemState) {
            case OFF:
                color124(Color.BLACK);
                break;
            case GREEN:
                color124(Color.RED);
                break;
            case BLUE:
                color124(Color.GREEN);
                break;
            case RED:
                color124(Color.BLUE);
                break;
            default:
                color124(Color.DARK_GRAY);
                break;
        }
    }

}
