package L5.training;

import TrainingUtils.KeyButton;

import javax.crypto.spec.PSource;
import java.awt.*;
import static L5.training.LEDgame.WantedState.IDLE;

public class LEDgame {
    private int length;
    private KeyButton B1;
    private KeyButton B2;
    private KeyButton B3;
    private LEDgame.SystemState systemState;
    private LEDgame.WantedState wantedState;
    private LED led;
    private int index=6;
    private int loop;
    public static final int speed=9;
    private int loops;
    private boolean whiteLoop;
    private boolean win;

    public LEDgame(int length) {
        led = new LED(15);
        whiteLoop=true;
        this.length = length;
        int loops = 0;
        B2 = new KeyButton(2);
        systemState = LEDgame.SystemState.RED;
        wantedState = IDLE;

    }
    public void periodic(){
        updateWantedState();
        systemState = handleStateTransition();
        applyState();
        loops++;
        if (loops%speed==0 && systemState==SystemState.GREEN){
            if (loop >0 && loop<12){
                if (whiteLoop){
                    loop++;
                }else {
                    loop--;
                }
            }else{
                if (loop<1){
                    loop=1;
                    whiteLoop=true;
                }else {
                    loop=11;
                    whiteLoop=false;
                }
            }
        }
    }
    public enum WantedState {
        B2,
        IDLE,
    }
    public enum SystemState {
        GREEN,
        RED,
    }
    public boolean winOrLose(){
        System.out.println(index + " " + loop);
        if ((index != loop) &&(index!=loop+1)&&(index!=loop+2)){
            return false;
        }else {
            return true;
        }
    }
    public void updateWantedState() {
        wantedState = IDLE;
        if (B2.isPressed()) {
            wantedState = WantedState.B2;
        }
    }

    public LEDgame.SystemState handleStateTransition() {
        if (wantedState == WantedState.B2){
            if (SystemState.RED == systemState){
                return SystemState.GREEN;
            }else {
                if (winOrLose()) {
                    index = (int)(Math.random()*15);
                }else {
                    return SystemState.RED;
                }
            }
        }
        return systemState;
    }
    public void applyState() {

        switch (systemState) {
            case GREEN:
                led.setColor(Color.black);
                led.setOneLed(Color.WHITE,loop,loop+1,loop+2);
                led.setOneLed(Color.GREEN,index,index,index);
                break;

            case RED:
                led.setOneLed(Color.GREEN,index,index,index);
                led.fullCOlor(Color.RED,0,length);
                break;
        }
    }
}