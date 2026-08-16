package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import javax.crypto.KeyAgreement;
import javax.swing.*;
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

    public LED(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());

        b1 = new KeyButton(1);
        b2 = new KeyButton(2);

        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;

    }

    public enum WantedState {
        CW,
        CCW,
        IDLE
    }

    private SystemState systemState;
    private WantedState wantedState;


    public void placeInBuffer(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());

    }

    public void periodic() {
        updateWantedState();
        systemState = handelStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (b1.isPressed())
            wantedState = WantedState.CW;
        if (b2.isPressed())
            wantedState = WantedState.CCW;
        else
            wantedState = WantedState.IDLE;
    }

    public void setOneLed(int index, Color color) {
        placeInBuffer(index, color);
        strip.setData(buffer);
    }

    public void fullColor(Color c) {
        for (int i = 0; i < length; i++) {
            placeInBuffer(i, c);
        }
        strip.setData(buffer);
    }

    public WantedState getWantedState() {
        return wantedState;

    }

    public SystemState handelStateTransition() {
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
            case CCW:
                switch (systemState) {
                    case OFF:
                        return SystemState.BLUE;
                    case BLUE:
                        return SystemState.RED;
                    case RED:
                        return SystemState.GREEN;
                    case GREEN:
                        return SystemState.BLUE;

                }
            case IDLE:
                return systemState;
        }
        return systemState;
    }
    public void applyState() {
        switch (systemState){
            case BLUE:
                fullColor(Color.blue);
                break;
            case RED:
                fullColor(Color.red);
                break;
            case GREEN:
                fullColor(Color.green);
                break;
            case OFF:
                fullColor(Color.black);
                break;
            default:
                fullColor(Color.black);
                break;

        }
    }
}
