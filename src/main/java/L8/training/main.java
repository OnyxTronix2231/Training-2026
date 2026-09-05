package L8.training;

import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        robot rubush=new robot();
        for (int i = 0; i < 1500; i++) {
            rubush.piriodic();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {}
        }
    }
}
