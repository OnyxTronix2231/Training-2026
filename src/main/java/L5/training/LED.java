package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

import static L5.training.LEDGAME.LEDGAMEWantedState.*;

public class LED {
    private int Length;
    private AddressableLEDSim addressableLEDSim;
    private AddressableLEDBuffer Buffer;


    public LED(int Length) {
        this.Length = Length;
        addressableLEDSim = new AddressableLEDSim();
        Buffer = new AddressableLEDBuffer(Length);
        addressableLEDSim.setLength(Buffer.getLength());
    }

    public void Colorbyindex(int index, Color color) {
        Buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
        addressableLEDSim.setData(Buffer);

    }

    public void fullcolor(Color color2) {
        for (int i = 0; i < Length; i++) {
            Buffer.setRGB(i, color2.getRed(), color2.getGreen(), color2.getBlue());
        }
        addressableLEDSim.setData(Buffer);

    }

    private void placeinbuffer(int index, Color color) {
        Buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setOneled(int index, Color color) {
        placeinbuffer(index, color);
    }

    public void skibidicolor(Color color, int startindex, int stopindex) {
        for (int i = startindex; i < stopindex + 1; i++) {
            placeinbuffer(i, color);
            addressableLEDSim.setData(Buffer);

        }


    }


}


