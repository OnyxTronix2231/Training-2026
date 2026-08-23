package L5.training;

import TrainingUtils.KeyButton;

import java.awt.*;

public class LEDGame {
    private KeyButton b1;
    private KeyButton b2;
    private KeyButton b3;
    private SystemState systemState;
    private WantedState wantedState;
    private LEDFunctions functions;
    private int index;
    private int whitePosition;
    private final int frameLength = 4;
    private int frameAmount ;

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

    public void MoveWhite() {
        whitePosition++;
        if (frameAmount == frameLength) {
        functions.Setled(whitePosition, Color.WHITE);}
        if (whitePosition == 14) {
            whitePosition = 0;
        }
    }

    public void ChooseIndex() {
        double num = Math.random() * 15;
        index = (int) (num);
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
                    ChooseIndex();
                    return ChooseColour();

                } else {
                    return systemState;
                }
            case B2:
                if (systemState == SystemState.GREEN) {
                    ChooseIndex();
                    return ChooseColour();
                } else {
                    return systemState;
                }
            case B3:
                if (systemState == SystemState.BLUE) {
                    ChooseIndex();
                    return ChooseColour();
                } else {
                    return systemState;
                }
            default:
                return systemState;
        }
    }

    public void applystate() {
        functions.fullcolour(Color.BLACK);
        frameAmount++;
        MoveWhite();
        switch (systemState) {
            case BLUE:
                functions.Setled(index, Color.BLUE);
                break;
            case GREEN:
                functions.Setled(index, Color.GREEN);
                break;
            case RED:
                functions.Setled(index, Color.RED);
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
