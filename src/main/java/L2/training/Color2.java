package L2.training;


import edu.wpi.first.math.MathUtil;

public class Color2{
        private int red;
        private int blue;
        private int green;
        Color2(int newred,int newblue,int newgreen){
            red= MathUtil.clamp(newred,0,255);
            blue=MathUtil.clamp(newblue,0,255);
            green=MathUtil.clamp(newgreen,0,255);;

        }
        public void clear(){
            red=0;
            blue=0;
            green=0;


        }


        public static void main(String[] args) {
            Color2 color=new Color2(255,255,255);
            color.clear();
            System.out.println(color.green);
            System.out.println(color.blue);
            System.out.println(color.red);
        }
    }

