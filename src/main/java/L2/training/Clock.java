package L2.training;

public class Clock {
    private int secondes;
    private int minutes;
    private int hours;

    public Clock(int secondes, int minutes, int hours) {
        this.secondes = secondes;
        this.minutes = minutes;
        this.hours = hours;
    }

    public void resetTime(int sec, int min, int hour){
        this.secondes = sec;
        this.minutes = min;
        this.hours = hour;
    }

    public int getSecondes() {
        return secondes;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setSecondes(int secondes) {
        this.secondes = secondes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void sayTime(){
        System.out.println("the time is " + getHours() + " hours, " + getMinutes()+ " minutes and " + getSecondes() + " seconds");
    }

    public static void main(String[] args) {
    }

}
