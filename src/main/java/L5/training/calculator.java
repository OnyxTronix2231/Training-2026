package L5.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class calculator {
    public int calcolator(int number) {
        int result = 1;
        while (number != 0) {
            result = result * number;
            number = number - 1;
            return result;
        }
        return result;

    }
}