package L2.training;

public class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours(Clock c) {
        return c.hours;
    }
    public int getMinutes(Clock c) {
        return c.minutes;
    }
    public int getSeconds(Clock c) {
        return c.seconds;
    }
    public void setHours(Clock c, int value) {
        c.hours = value;
    }
    public void setMinutes(Clock c, int value) {
        c.minutes = value;
    }
    public void setSeconds(Clock c, int value) {
        c.seconds = value;
    }
    public void clearHours(Clock c) {
        c.hours = 0;
    }
    public void clearMinutes(Clock c) {
        c.minutes = 0;
    }
    public void clearSeconds(Clock c) {
        c.seconds = 0;
    }
    public void sayTime(Clock c) {
        System.out.println("the time is "+c.hours+":"+c.minutes+":"+c.seconds);
    }

}
