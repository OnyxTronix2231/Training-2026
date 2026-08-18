package L5.training;

import TrainingUtils.KeyButton;

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
    private int index;
    private int loop;
    public static final int speed=9;
    private int loops;
    private boolean whiteLoop;

    public LEDgame(int length) {
        led = new LED(15);
        whiteLoop=true;
        this.length = length;
        int loops = 0;
        B1 = new KeyButton(1);
        B2 = new KeyButton(2);
        B3 = new KeyButton(3);
        systemState = LEDgame.SystemState.RED;
        wantedState = IDLE;

    }
    public void periodic(){
        updateWantedState();
        systemState = handleStateTransition();
        applyState();
        loops++;
        if (loops%speed==0 && systemState==SystemState.GREEN){
            System.out.println(loop);
            if (loop >0 && loop<14){
                if (whiteLoop==true){
                    loop++;
                }else {
                    loop--;
                }
            }else{
                if (loop<1){
                    loop=1;
                    whiteLoop=true;
                }else {
                    loop=13;
                    whiteLoop=false;
                }
            }
        }

    }
    public enum WantedState {
        B1,
        B2,
        B3,
        IDLE,
    }
    public enum SystemState {
        RED,
        GREEN,
        BLUE
    }
    public void updateWantedState() {
        wantedState = IDLE;
        if (B1.isPressed()) {
            wantedState = WantedState.B1;

        }
        if (B2.isPressed()) {
            wantedState = WantedState.B2;
        }
        if (B3.isPressed()) {
            wantedState = WantedState.B3;
        }
    }
    public LEDgame.SystemState handleStateTransition() {
        switch(wantedState){
            case B1:
                if (systemState== SystemState.RED){
                    index=(int)(Math.random()*(15));
                    if (Math.random()>0.5){
                        return SystemState.GREEN;
                    }else{
                        return SystemState.BLUE;
                    }
                }
                break;
            case B2:
                if (systemState== SystemState.GREEN){
                    index=(int)(Math.random()*(15));
                    if (Math.random()>0.5){
                        return SystemState.RED;
                    }else{
                        return SystemState.BLUE;
                    }
                }
                break;
            case B3:
                if (systemState== SystemState.BLUE){
                    index=(int)(Math.random()*(15));
                    if (Math.random()>0.5){
                        return SystemState.GREEN;
                    }else{
                        return SystemState.RED;
                    }
                }
                break;
        }
        return systemState;
    }
    public void applyState() {
        led.setColor(Color.black);
        led.setOneLed(Color.WHITE,loop);
        switch (systemState) {
            case GREEN:
                led.setOneLed(Color.GREEN,index);
                break;
            case BLUE:
                led.setOneLed(Color.BLUE,index);
                break;
            case RED:
                led.setOneLed(Color.RED,index);
                break;
            default:
                led.setOneLed(Color.DARK_GRAY,index);
                break;
        }
    }
}
