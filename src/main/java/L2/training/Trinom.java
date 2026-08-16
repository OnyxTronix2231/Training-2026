package L2.training;

public class Trinom {

    private double squared;
    private double variable;
    private double normal;

    public Trinom(int squared, int variable, int normal) {
        this.squared = squared;
        this.variable = variable;
        this.normal = normal;
    }
    public void addValues(int square, int var, int num) {
        squared += square;
        variable += var;
        normal += num;
    }
    public void setSquared(double square) {squared = square;}
    public void setVariable(double var) {variable = var;}
    public void setNormal(double num) {normal = num;}

    public double getSquared() {return squared;}
    public double getVariable() {return variable;}
    public double getNormal() {return normal;}

    public void printTrinom() {
        System.out.println(squared+"x^2 + "+variable+"x + "+normal);
    }

    public void solveTrinom() {
        double root = Math.sqrt(Math.pow(variable,2)-4*squared*normal);
        double solutionPlus = (-1*variable+root)/2*squared;
        double solutionMinus = (-1*variable-root)/2*squared;
        System.out.print("x1 = "+solutionPlus+"x2 = "+solutionMinus);
    }
}
