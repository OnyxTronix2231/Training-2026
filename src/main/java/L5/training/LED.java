package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import java.awt.*;

import static L5.training.LED.WantedState.IDLE;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton b1;
    private KeyButton b2;
    private KeyButton b3;

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }

    public enum WantedState {
        G,
        R,
        B,
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
        b3 = new KeyButton(3);


        systemState = SystemState.OFF;
        wantedState = IDLE;
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
    updateWantedState();
    systemState = handleStateTransition();
    applyState();
    }

    public void updateWantedState() {
        wantedState = IDLE;
        if (b1.isPressed()) {
            wantedState = WantedState.R;
        }
        if (b2.isPressed()) {
            wantedState = WantedState.B;
        }
        if (b3.isPressed()) {
            wantedState = WantedState.G;
        }
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case R:
                switch (systemState) {
                    case OFF:
                        return SystemState.RED;
                    case RED:
                        return SystemState.OFF;
                    default:
                        return systemState;
                }
            case G:
                switch (systemState) {
                    case OFF:
                        return SystemState.GREEN;
                    case GREEN:
                        return SystemState.OFF;
                    default:
                        return systemState;
                }
            case B:
                switch (systemState) {
                    case OFF:
                        return SystemState.BLUE;
                    case BLUE:
                        return SystemState.OFF;
                    default:
                        return systemState;
                }
            case IDLE:
                return systemState;
        }
        return systemState;
    }

    public void applyState() {
        switch (systemState) {
            case OFF:
                lightAll(Color.BLACK);
                break;
            case BLUE:
                lightAll(Color.BLUE);
                break;
            case GREEN:
                lightAll(Color.GREEN);
                break;
            case RED:
                lightAll(Color.RED);
                break;
            default:
                lightAll(Color.BLACK);
                break;
        }
    }
}

