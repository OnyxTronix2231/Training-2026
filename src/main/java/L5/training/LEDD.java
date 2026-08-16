package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LEDD {
    private int Length;
    private AddressableLEDSim addressableLEDSim;
    private AddressableLEDBuffer Buffer;
    private KeyButton B1;
    private KeyButton B2;
    private WantedState wantedState;
    private SystemState systemState;

    public LEDD(int Length) {
        this.Length = Length;
        addressableLEDSim = new AddressableLEDSim();
        Buffer = new AddressableLEDBuffer(Length);
        addressableLEDSim.setLength(Buffer.getLength());
        B1 = new KeyButton(1);
        B2 = new KeyButton(2);
        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;


    }

    public void Color(int index, Color color) {
        Buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
        addressableLEDSim.setData(Buffer);


    }

    public void fullcolor(Color color2) {
        for (int i = 0; i < Length; i++) {
            Buffer.setRGB(i, color2.getRed(), color2.getGreen(), color2.getBlue());
        }
        addressableLEDSim.setData(Buffer);

    }

    private void placeinbuffer(int index, Color color) {
        Buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setOneled(int index, Color color) {
        placeinbuffer(index, color);
    }

    public void skibidicolor(Color color, int startindex, int stopindex) {
        for (int i = startindex; i < stopindex + 1; i++) {
            placeinbuffer(i, color);
            addressableLEDSim.setData(Buffer);

        }


    }

    public void periodic() {
        updatewantedstate();
        systemState = handleStateTransition();
        applycolor();

    }

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE,
    }

    public enum WantedState {
        togglegreen,
        toggleblue,
        togglered,
        IDLE,
    }

    public void updatewantedstate() {
        if (B1.isPressed()) {
            wantedState = WantedState.toggleblue;
        } else if (B2.isPressed()) {
            wantedState = WantedState.togglered;
        } else {
            wantedState = WantedState.togglegreen;
        }
    }

    public void applycolor() {
        switch (systemState) {
            case OFF:
                fullcolor(Color.BLACK);
                break;
            case BLUE:
                fullcolor(Color.BLUE);
                break;
            case RED:
                fullcolor(Color.RED);
                break;
            case GREEN:
                fullcolor(Color.GREEN);
                break;
        }
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case toggleblue:
                switch (systemState) {
                    case OFF:
                        return SystemState.BLUE;
                    default:
                        return systemState;

                }
            case togglegreen:
                switch (systemState) {
                    case OFF:
                        return SystemState.GREEN;

                    default:
                        return systemState;

                }
            case togglered:
                switch (systemState) {
                    case OFF:
                        return SystemState.RED;
                    default:
                        return systemState;
                }
            default:
                return systemState;


        }
    }
}




