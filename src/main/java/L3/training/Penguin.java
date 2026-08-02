package L3.training;

import edu.wpi.first.math.MathUtil;

public class Penguin {
    private int age;
    private String name;

    public Penguin(int age, String name) {
        this.age = Math.abs(age);
        if (age<0) {
            age = -age;
        }
        this.name = name;
    }

    public void setAge(int newAge) {age = MathUtil.clamp(newAge,0, 120);}
    public int getAge() {return age;}

    public void sayName() {
        String text = "hello " + name;
        System.out.println(text);
    }

    public void compare(Penguin penguin) {
        String text;
        if (this.age > penguin.age) {
            text = "The older penguin is " + this.name;
        } else if (penguin.age > this.age) {
            text = "The older penguin is " + penguin.name;
        } else {
            text = "Same age";
        }
        System.out.println(text);
    }

    @Override
    public String toString() {
        String penguinMassage = "Name of the penguin: " + name + "\n" + "Age of the penguin: " + age;
        return penguinMassage;
    }

    public static void main(String[] args) {
        Penguin myPenguin = new Penguin(14, "Yoav");
        myPenguin.sayName();
        Penguin Penguin1 = new Penguin(56, "Magniv");
        Penguin Penguin2 = new Penguin(64, "banana");
        Penguin1.compare(Penguin2);
        System.out.println(myPenguin.toString());
    }
}
