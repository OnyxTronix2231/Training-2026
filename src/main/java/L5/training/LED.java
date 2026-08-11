package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {

    private int length;
    private AddressableLEDSim strip;;
    private AddressableLEDBuffer buffer;
    private KeyButton B1;
    private KeyButton B2;

    public LED(int lengt){
        this.length=lengt;
        strip=new AddressableLEDSim();
        buffer=new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        B1 = new KeyButton(1);
        B2 = new KeyButton(2);
        systemState = SystemState.OFF;
        wantedState = WantedState.IDLE;
    }
    private void placeInBuffer(int index,Color color){
        buffer.setRGB(index,color.getRed(),color.getGreen(),color.getBlue());
    }
    public void color123(Color color,int index,int index1){
       placeInBuffer(index,color);
        placeInBuffer(index1,color);
        strip.setData(buffer);
    }
    public void color124(Color c){
        for (int i=0;i<length;i++){
            placeInBuffer(i,c);

        }
        strip.setData(buffer);
    }
    public void fullCOlor(Color a,int index1,int index2){
        for (int i = index1; i <index2; i++) {
            placeInBuffer(i,a);
        }
        strip.setData(buffer);
    }
    public enum WantedState {
        CW,
        CWW,
        IDLE,
    }

    public enum SystemState {
        OFF,
        RED,
        GREEN,
        BLUE
    }
    public void updateWantedState(){
        wantedState=WantedState.IDLE;
        if (B1.isPressed()) {
            wantedState = WantedState.CW;
        } if (B2.isPressed()) {
            wantedState=WantedState.CWW;
        }



    }
    private SystemState systemState;
    private WantedState wantedState;
    public void periodic(){
    }
}
