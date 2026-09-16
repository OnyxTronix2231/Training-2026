package L7.training;

public class Programmer extends Worker{
    private String programmingLeng;
    private String subject;
    private int bonus;
    private int gifted;

    public Programmer(String name, String workerNUm, String ID, int age, String job, int salary) {
        super(name, workerNUm, ID, age, job, salary);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "programmingLeng='" + programmingLeng + '\'' +
                ", subject='" + subject + '\'' +
                ", bonus=" + bonus +
                ", gifted=" + gifted +
                '}';
    }

    @Override
    public int monthSalary(int hoursThisMonth){
        return (hoursThisMonth * salary * gifted * 10) + bonus;
    }
}
