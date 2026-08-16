package L3.training;

import edu.wpi.first.math.MathUtil;

public class Penguin {
    private int age;
    private String name;

    public int getAge() {return age;}
    public String getName() {return name;};

    public void setAge(int newAge) {age = MathUtil.clamp(Math.abs(newAge),0,19);}
    public void setName(String newName) {name = newName;}

    public String toString() {
        return "Penguin: \n\t"
                +name+ "\n\t"
                +age;
    }

    public void sayName() {
        System.out.println("Hello"+name);
    }
    public void whoOlder (Penguin p1,Penguin p2) {
        if (p1.age>p2.age)
            System.out.println("The older penguin is "+p1.name);
        if (p2.age>p1.age)
            System.out.println("The older penguin is "+p2.name);
    }
}
