package L2.training;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void moveDot(double x, double y){
        setX(getX()+x);
        setY(getY()+y);
    }

    public double disFromMid(){
        double dis = Math.hypot(x, y);
        return dis;

    }
}
