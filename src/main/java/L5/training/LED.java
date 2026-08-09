package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;;
    private AddressableLEDBuffer buffer;
    public LED(int lengt){
        this.length=lengt;
        strip=new AddressableLEDSim();
        buffer=new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
    }
    public void color123(Color color,int index){
        buffer.setRGB(index, color.getRed(), color.getGreen(),color.getBlue());
        strip.setData(buffer);
    }
    public void color124(Color color){
        for (int i=0;i<length;i++){
            buffer.setRGB(i, 0, 110, 250);

        }
        strip.setData(buffer);
    }
}
