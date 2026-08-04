package L4.training;

public class sfsdf {
    private static int num=0;
    private static int shirt=0;
    private size Size;
    private int studentshirt;
    public sfsdf(size Size) {
        num++;
        this.Size = Size;

    }

    public enum size{
        M,
        L,
        XL,
    }
    public void buyshirt(){
        shirt++;
    }

    public static boolean canbuy(){
        return shirt>5;

    }
    public static void main(String[] args) {
        sfsdf student= new sfsdf(size.M);
        student.buyshirt();
        canbuy();
        System.out.println(student.Size);
        System.out.println(canbuy());
        System.out.println(shirt);


    }
}



