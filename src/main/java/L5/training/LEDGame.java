package L5.training;

import TrainingUtils.KeyButton;

import java.awt.*;

import static L5.training.LEDGame.SystemState.GREEN;

public class LEDGame {
    public static final int speed = 5;
    public static final int whiteLength = 3;

    private LED led;
    private KeyButton b1;
    private KeyButton b2;
    private KeyButton b3;
    private int index;
    private int minIndexWhite;
    private int maxIndexWhite;
    private int loop;
    private boolean whiteLoopDirection;


    public LEDGame(int length) {
        this.led = new LED(15);
        systemState = SystemState.RED;
        wantedState = WantedState.IDLE;
        b1 = new KeyButton(1);
        b2 = new KeyButton(2);
        b3 = new KeyButton(3);
        loop = 1;
        boolean whiteLoopDirection = true;
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
        IDLE
    }

    private SystemState systemState;
    private WantedState wantedState;

    public void periodic() {
        updateWantedState();
        systemState = handleStateTransition();
        applyState();
        loop++;
        if (loop % speed == 0) {
            moveWhitePoint();
        }
    }

    public void moveWhitePoint() {
        if (systemState == GREEN) {
            if (loop % 15 < 14) {
                if (whiteLoopDirection) {
                minIndexWhite = (minIndexWhite + 1) % 12;
                } else if (!whiteLoopDirection && loop % 15 > 0) {
                    minIndexWhite = (minIndexWhite - 1) % 12;
                }
            } else {
                whiteLoopDirection = !(whiteLoopDirection);
            }
            maxIndexWhite = minIndexWhite + 3;
        }
    }


    public void updateWantedState() {
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

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case B1:
                if (systemState == SystemState.RED) {
                    index = (int) (Math.random() * (led.getLength() - 1));
                    double a = Math.random();
                    if (a <= 0.5) {
                        return SystemState.GREEN;
                    } else {
                        return SystemState.BLUE;
                    }
                } else {
                    return systemState;
                }
            case B2:
                if (systemState == GREEN) {
                    index = (int) (Math.random() * (led.getLength() - 1));
                    double a = Math.random();
                    if (a <= 0.5) {
                        return SystemState.BLUE;
                    } else {
                        return SystemState.RED;
                    }
                } else {
                    return systemState;
                }
            case B3:
                if (systemState == SystemState.BLUE) {
                    index = (int) (Math.random() * (led.getLength() - 1));
                    double a = Math.random();
                    if (a <= 0.5) {
                        return SystemState.RED;
                    } else {
                        return SystemState.GREEN;
                    }
                } else {
                    return systemState;
                }
        }
        return systemState;
    }

    public void applyState() {
        led.lightAll(Color.BLACK);
        led.paintRange(Color.WHITE, minIndexWhite, maxIndexWhite);
        switch (systemState) {
            case BLUE:
                led.lightOneLed(Color.BLUE, index);
                break;
            case GREEN:
                led.lightOneLed(Color.GREEN, index);
                break;
            case RED:
                led.lightOneLed(Color.RED, index);
                break;
            default:
                led.lightAll(Color.BLACK);
                break;
        }
    }
}
