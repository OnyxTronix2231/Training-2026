package L8.training;

public class Robot implements Breakable{
    private String movingDirection;
    private int speed;
    private boolean broken;

    public Robot(String movingDirection, int speed) {
        this.movingDirection = movingDirection;
        this.speed = speed;
        this.broken = false;
    }

    public void increaseSpeed(){
        speed *= 2;
    }

    public void decreaseSpeed(){
        speed /= 2;
    }

    @Override
    public int breatIt(double speed){
        return (int) Math.abs(speed) / 2;
    }

    @Override
    public boolean fix() {
        return false;
    }
}
