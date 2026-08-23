package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

import static L5.training.LEDGAME.LEDGAMESystemState.*;
import static L5.training.LEDGAME.LEDGAMEWantedState.*;


public class LEDGAME {
    private int Length;
    private KeyButton B1;
    private KeyButton B2;
    private KeyButton B3;
    private LEDGAMESystemState systemState;
    private LEDGAMEWantedState wantedState;
    private int index;
    private LED led;
    private int whiteindex;
    private final int primelength;
    private int count;
    private boolean Isright;


    public LEDGAME(int length) {
        this.Length = length;
        B1 = new KeyButton(1);
        B2 = new KeyButton(2);
        B3 = new KeyButton(3);
        systemState = RED;
        wantedState = LEDGAMEWantedState.IDLE;
        led = new LED(length);
        primelength=20;
        count=0;
        Isright=true;
    }
    public void updatewantedstate() {
        if (B1.isPressed()) {
            wantedState = CW;
        } else if (B2.isPressed()) {
            wantedState = CCW;
        } if (B3.isPressed()){
            wantedState = IDLE;
        }
    }

    public void periodic() {
        updatewantedstate();
        systemState = handleStateTransition();
        applycolor();

    }


    public void   chooseindex(){
        double num= Math.random()*Length;
        index = (int) (num);
    }

    public void setWhiteindex(){
        if (whiteindex==Length-1){
            Isright=false;
        }
        if (whiteindex==0){
            Isright=true;
        }
        if (count==primelength){
            count=0;
            if (Isright==false){
                whiteindex--;

            }else{
                whiteindex++;
            }
        }






    }

    public  LEDGAMESystemState randomcolor(){
        double num = Math.random();
        switch (systemState){
            case BLUE:
                if(num>0.5){
                    return RED;
                } else if (num<0.5) {
                    return GREEN;

                }
            case GREEN:
                if(num>0.5){
                    return RED;
                }else if (num<0.5){
                    return BLUE;
                }
            case RED:
                if (num>0.5){
                    return BLUE;
                }else{
                    return GREEN;
                }
            default:
                return systemState;
        }
    }

    public LEDGAMESystemState handleStateTransition() {
        switch (wantedState) {
            case CW:
                switch (systemState) {
                    case RED:
                        chooseindex();
                        return randomcolor();

                    //case RED:
                        //return LEDGAMESystemState.BLUE;
                    //case BLUE:
                        //return LEDGAMESystemState.GREEN;
                    //case GREEN:
                        //return LEDGAMESystemState.RED;
                    default:
                        return (systemState);

                }
            case CCW:
                switch (systemState) {
                    //case OFF:
                     //   return LEDGAMESystemState.OFF;
                    //case BLUE:
                      //  return LEDGAMESystemState.RED;
                    case GREEN:
                        chooseindex();
                        setWhiteindex();
                        return randomcolor();
                    //case GREEN:
                        //return LEDGAMESystemState.BLUE;
                    default:
                        return systemState;

                }
            case IDLE:
                switch (systemState){
                    case BLUE:
                        return randomcolor();

                    default:
                        return systemState;

                }
            case Start:
                switch (systemState){
                    case OFF:
                        return RED;

                }

            default:
                return systemState;


        }
    }

    public enum LEDGAMESystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }
    public enum LEDGAMEWantedState {
        CW,
        CCW,
        IDLE,
        Start,
    }

    public void applycolor() {

        led.fullcolor(Color.black);
        switch (systemState) {
            case OFF:
                led.fullcolor(Color.BLACK);
                break;
            case BLUE:
                led.Colorbyindex(index,Color.blue);
                break;
            case RED:
                led.Colorbyindex(index,Color.red);
                break;
            case GREEN:
                led.Colorbyindex(index,Color.green);
                setWhiteindex();
                break;
        }
    }





}

