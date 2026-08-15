package L3.training;

import edu.wpi.first.math.MathUtil;

public class Main {
    public static void main(String[] args) {
        Ifs.ifBiggerThen5(8);
        //System.out.println(Utils.clamp(-3, 10, 1));
        System.out.println(MathUtil.clamp(-3, 1, 10));
    }
}
