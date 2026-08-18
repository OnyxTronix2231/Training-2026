package L5.training;

import TrainingUtils.KeyButton;
import java.util.Random;

import java.awt.*;

public class LEDgame {
    private LED led;
    private SystemState currentState;
    private WantedState wantedState;
    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;
    private int index;
    private int whiteDotIndex;
    private Color color;
    public static final int speed = 3;
    private int movingDirection;
    public int counter;
    public static final int whiteLineLenght = 3;

    public enum SystemState{
        playing,
        notPlaying
    }

    public enum WantedState{
        green,
        IDLE,
    }

    public LEDgame(){
        this.led = new LED(15);
        this.currentState = SystemState.playing;
        this.wantedState = WantedState.IDLE;
        this.button1 = new KeyButton(1);
        this.button2 = new KeyButton(2);
        this.button3 = new KeyButton(3);
        this.color = new Color(255, 0, 0);
        this.whiteDotIndex = 0;
        this.counter = 0;
        this.movingDirection = 1;
    }

    public void moveWhiteDot(){
        counter ++;
        if (counter == speed){
            counter = 0;
            whiteDotIndex = whiteDotIndex + movingDirection;
            if (whiteDotIndex > 10 || whiteDotIndex < 1){
                movingDirection = movingDirection * -1;
            }
        }
    }

    public boolean dotOnBar(){
        return whiteDotIndex <= index && whiteDotIndex + whiteLineLenght >= index;

    }
    public void updateWantedState(){
        wantedState = WantedState.IDLE;
        if(button1.isPressed()) {
            wantedState = WantedState.green;
        }
    }

    public SystemState handleStateTransition(){
        Random random = new Random();
        if (wantedState.equals(WantedState.green)){
            switch (currentState){
                case notPlaying:
                    index = random.nextInt(15);
                    return SystemState.playing;
                case playing:
                    if (dotOnBar()){
                        index = random.nextInt(15);
                        return SystemState.playing;
                    }
                    else {
                        return SystemState.notPlaying;
                    }
                default:
                    return currentState;
            }
        }
        return currentState;
    }

    public void applyState(){
        switch (currentState){
            case notPlaying:
                led.powerAllLed(Color.red);
                break;
            case playing:
                color = Color.green;
                led.powerAllLed(Color.black);
                led.powerOneLed(color, index);
                led.range(Color.white, whiteDotIndex, whiteDotIndex + whiteLineLenght);
                moveWhiteDot();
                break;
            default:
                color = Color.black;
        }
    }

    public void periodic(){
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }
}
