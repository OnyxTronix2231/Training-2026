package L5.training;

import TrainingUtils.KeyButton;

import java.awt.*;

import static L5.training.LEDGame.SystemState.*;

public class LEDGame {
    public static final int speed = 10;
    // bigger = slower
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
        systemState = SystemState.loseMode;
        wantedState = WantedState.IDLE;
        b2 = new KeyButton(2);
        loop = 1;
        whiteLoopDirection = true;
        minIndexWhite = 11;
        index = (int) (Math.random() * (led.getLength() - 1));
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
        //if (whiteLoopDirection) {
        //  minIndexWhite++;
        //} else {
        minIndexWhite--;
        minIndexWhite = ((minIndexWhite + 10) % 12 + 1);
        //}
        maxIndexWhite = minIndexWhite + 2;
        //if (maxIndexWhite >= 14 && whiteLoopDirection) {
        //  whiteLoopDirection = false;
        //} else if (minIndexWhite <= 0 && !whiteLoopDirection) {
        //  whiteLoopDirection = true;
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
                    systemState = systemState.gameMode;
                } else {
                    return systemState;
                }
            case gameMode:
                boolean isCaught =(index == minIndexWhite || index == minIndexWhite + 1 || index == maxIndexWhite);
                if (isCaught){
                index = (int) (Math.random() * (led.getLength() - 1));
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
                led.lightOneLed(Color.GREEN, index);
                break;
            default:
                led.lightAll(Color.BLACK);
                break;
        }
    }
}
