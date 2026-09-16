package L7.training;

public class Worker {
    protected String name;
    protected String  workerNUm;
    protected String ID;
    protected int age;
    protected String job;
    protected int salary;

    public Worker(String name, String workerNUm, String ID, int age, String job, int salary) {
        this.name = name;
        this.workerNUm = workerNUm;
        this.ID = ID;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String  getWorkerNUm() {
        return workerNUm;
    }

    public String getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkerNUm(String workerNUm) {
        this.workerNUm = workerNUm;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", workerNUm='" + workerNUm + '\'' +
                ", ID='" + ID + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int monthSalary(int hoursThisMonth){
        return hoursThisMonth * salary;
    }


}
