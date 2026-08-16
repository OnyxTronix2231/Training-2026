package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton button1;
    private KeyButton button2;

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }
    public enum WantedState {
        IDLE,
        CW,
        CCW
    }

    private SystemState systemState;
    private WantedState wantedState;


    public LED (int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;
    }
    public void setLength(int newLength) {length = newLength;}
    public void setStrip(AddressableLEDSim newStrip) {strip = newStrip;}
    public void setBuffer(AddressableLEDBuffer newBuffer) {buffer = newBuffer;}

    public int getLength() {return length;}
    public AddressableLEDSim getStrip() {return strip;}
    public AddressableLEDBuffer getBuffer() {return buffer;}

    private void placeInBufferRGB(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setLed(int index, Color color) {
        placeInBufferRGB(index, color);
        strip.setData(buffer);
    }

    public void allStrip(Color color) {
        for (int i=0;i<length;i++) {
            placeInBufferRGB(i, color);
            strip.setData(buffer);
        }
    }
    public void range(int index1, int index2, Color color) {
        for (int i=index1;i<=index2;i++) {
            placeInBufferRGB(i,color);
            strip.setData(buffer);
        }
    }

    public void rainbow() {
        int jump = 180/length;
        for (int i=0;i<length;i++) {
            buffer.setHSV(i,jump*i,255,255);
            strip.setData(buffer);
        }
    }

    public void periodic() {
        updateWantedState();
        systemState = handelStateTransition();
        applyState();
    }

    public void updateWantedState() {
        wantedState = WantedState.IDLE;
        if (button1.isPressed()) {
            wantedState = WantedState.CW;
            System.out.println("b1");
        }
        if (button2.isPressed()) {
            wantedState = WantedState.CCW;
            System.out.println("b2");
        }
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
                        return SystemState.OFF;
                    case BLUE:
                        return SystemState.RED;
                    case GREEN:
                        return SystemState.BLUE;
                    case RED:
                        return SystemState.GREEN;
                }
            case IDLE:
                return systemState;
            default:
                return systemState;

        }
    }
    public void applyState() {
        switch (systemState) {
            case RED:
                allStrip(Color.RED);
                break;
            case BLUE:
                allStrip(Color.BLUE);
                break;
            case GREEN:
                allStrip(Color.GREEN);
                break;
            case OFF:
                allStrip(Color.BLACK);
                break;
        }
    }

}
