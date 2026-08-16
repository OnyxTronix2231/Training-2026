package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED2 {
    private int LEDLength;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton b1;
    private KeyButton b2;
    private KeyButton b3;
    private SystemState systemState;
    private WantedState wantedState;

    public LED2(int length) {
        this.LEDLength = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        b1 = new KeyButton(1);
        b2 = new KeyButton(2);
        b3 = new KeyButton(3)
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

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case CW:
                switch (systemState) {
                    case OFF:
                        return SystemState.BLUE;
                    case BLUE:
                        return SystemState.GREEN;
                    case GREEN:
                        return SystemState.RED;
                    case RED:
                        return SystemState.BLUE;

                }
                break;
            case CCW:
                switch (systemState) {
                    case OFF:
                        return SystemState.OFF;
                    case BLUE:
                        return SystemState.RED;
                    case GREEN:
                        return SystemState.BLUE;
                    case RED:
                        return SystemState.GREEN;
                }
                break;
            case IDLE:
                switch (systemState) {
                    case OFF:
                        return SystemState.OFF;
                    case BLUE:
                        return SystemState.BLUE;
                    case GREEN:
                        return SystemState.GREEN;
                }
                break;
        }
        return systemState;
    }
    public void applystate() {
        switch (systemState) {
            case OFF:
                fullcolour(Color.BLACK);
                break;
            case BLUE:
                fullcolour(Color.BLUE);
                break;
            case GREEN:
                fullcolour(Color.GREEN);
                break;
            case RED:
                fullcolour(Color.RED);
                break;
        }

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
        updateWantedState();
        systemState = handleStateTransition();
        applystate();
    }

}
