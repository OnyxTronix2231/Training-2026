package L2.training;

public class Circle {
    private Point point;
    private double radius;

    public Circle(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    public Point getPoint() {
        return point;
    }

    public double getRadius() {
        return radius;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void moveCircleBy(Point point, double x, double y){
        point.moveDot(x, y);
    }

    public void increaseRadiusBy(double num){
        setRadius(getRadius()+num);
    }

    public double circleH(){
        return 2 * Math.PI * getRadius();
    }

    public double circleS(){
        return Math.PI * getRadius()* getRadius();
    }
}