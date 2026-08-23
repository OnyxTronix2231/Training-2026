package L5.training;

import TrainingUtils.KeyButton;

import java.awt.*;
import static L5.training.LEDGame.SystemState.*;

public class LEDGame {
    public static final int speed = 8;
    // bigger = slower
    public static final int whiteLength = 3;
    //public static final int range = 3;

    private LED led;
    //private KeyButton b1;
    private KeyButton b2;
    //private KeyButton b3;
    private int index;
    private int minIndexWhite;
    private int maxIndexWhite;
    private int loop;
    private boolean whiteLoopDirection;
    private int blue;
    private Color c;


    public LEDGame(int length) {
        this.led = new LED(20);
        systemState = SystemState.loseMode;
        wantedState = WantedState.IDLE;
        b2 = new KeyButton(2);
        loop = 1;
        whiteLoopDirection = true;
        minIndexWhite = 0;
        index = (int) (Math.random() * (led.getLength() - 1));
        blue = 0;
        c = Color.GREEN;
    }

    public enum SystemState {
        loseMode,
        gameMode,
    }

    public enum WantedState {
        B2,
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
        if (whiteLoopDirection) {
            minIndexWhite++;
        } else {
            minIndexWhite--;
        }
        maxIndexWhite = minIndexWhite + whiteLength - 1;
        if (maxIndexWhite >= led.getLength() - 1 && whiteLoopDirection) {
            whiteLoopDirection = false;
        } else if (minIndexWhite <= 0 && !whiteLoopDirection) {
            whiteLoopDirection = true;
        }
    }

    public void updateWantedState() {
        if (b2.isPressed()) {
            wantedState = WantedState.B2;
        }
    }

    public SystemState handleStateTransition() {
        switch (systemState) {
            case loseMode:
                if (wantedState == wantedState.B2) {
                    wantedState = WantedState.IDLE;
                    c = Color.GREEN;
                    blue = 0;
                    systemState = SystemState.gameMode;
                    break;
                } else {
                    return systemState;
                }
            case gameMode:
                if (wantedState == wantedState.B2) {
                    boolean isCaught = (index >= minIndexWhite && index <= maxIndexWhite);
                    if (isCaught) {
                        wantedState = WantedState.IDLE;
                        c = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
                        index = (int) (Math.random() * (led.getLength() - 1));
                        blue ++;
                    } else {
                        wantedState = WantedState.IDLE;
                        systemState = SystemState.loseMode;
                    }
                }
        }
        return systemState;
    }

    public void applyState() {
        led.lightAll(Color.BLACK);
        led.paintRange(Color.WHITE, minIndexWhite, maxIndexWhite);
        switch (systemState) {
            case loseMode:
                led.lightAll(Color.RED);
                break;
            case gameMode:
                if (blue > 0) {
                led.paintRange(Color.BLUE, 0, blue - 1);
                }
                if (blue == led.getLength()) {
                    led.lightAll(Color.GREEN);
                }
                led.lightOneLed(c, index);
                break;
            default:
                led.lightAll(Color.BLACK);
                break;
        }
    }
}
