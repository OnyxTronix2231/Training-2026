package L2.training;


    public class Color2{
        private int red;
        private int blue;
        private int green;
        Color2(int newred,int newblue,int newgreen){
            red=newred;
            blue=newblue;
            green=newgreen;

        }
        public void clear(){
            red=0;
            blue=0;
            green=0;


        }
        public static int getfactor(int percentage){


        }

        public static void main(String[] args) {
            Color2 color=new Color2(255,255,255);
            color.clear();
            System.out.println(color.green);
            System.out.println(color.blue);
            System.out.println(color.red);
        }
    }

