package L5.training;

import TrainingUtils.KeyButton;
import edu.wpi.first.math.Num;

import java.awt.*;

public class LEDGame {
    private KeyButton b1;
    private KeyButton b2;
    private KeyButton b3;
    private SystemState systemState;
    private WantedState wantedState;
    private LEDFunctions functions;
    private int index;

    public LEDGame() {
        b1 = new KeyButton(1);
        b2 = new KeyButton(2);
        b3 = new KeyButton(3);
        systemState = SystemState.RED;
        wantedState = WantedState.IDLE;
        functions = new LEDFunctions(15);
    }

    public SystemState ChooseColour() {
        double ColourNum = Math.random();
        switch (systemState) {
            case RED:
                if (ColourNum < 0.5) {
                    return SystemState.BLUE;
                } else {
                    return SystemState.GREEN;
                }
            case BLUE:
                if (ColourNum < 0.5) {
                    return SystemState.GREEN;
                } else {
                    return SystemState.RED;

                }
            case GREEN:
                if (ColourNum < 0.5) {
                    return SystemState.RED;
                } else {
                    systemState = SystemState.BLUE;
                    return systemState;
                }
            default:
                return systemState;
        }
    }
    public int ChooseIndex() {
        double num = Math.random() * 15;
        index = (int) (num);
        return index;
    }


    public enum SystemState {
        RED,
        GREEN,
        BLUE
    }

    public enum WantedState {
        B1,
        B2,
        B3,
        IDLE,
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case B1:
                if (systemState == SystemState.RED) {
                    return ChooseColour();

                } else {
                    return systemState;
                }
            case B2:
                if (systemState == SystemState.GREEN) {
                    return ChooseColour();
                } else {
                    return systemState;
                }
            case B3:
                if (systemState == SystemState.BLUE) {
                    return ChooseColour();
                } else {
                    return systemState;
                }
            default:
                return systemState;
        }
    }

    public void applystate() {
        int NumIndex = ChooseIndex();
        switch (systemState) {
            case BLUE:
                functions.Setled(NumIndex,Color.BLUE);
                break;
            case GREEN:
                functions.Setled(NumIndex,Color.GREEN);
                break;
            case RED:
                functions.Setled(NumIndex,Color.RED);
                break;
        }


    }

    public void updateWantedState() {
        wantedState = wantedState.IDLE;
        if (b1.isPressed()) {
            wantedState = WantedState.B1;
        }
        if (b2.isPressed()) {
            wantedState = WantedState.B2;
        }
        if (b3.isPressed()) {
            wantedState = WantedState.B3;
        }
    }

    public void periodic() {
        updateWantedState();
        systemState = handleStateTransition();
        applystate();
    }
}
