package L8.training;

public class Vase implements Ibreakable {
    private boolean isBroken = false;
    public Vase(boolean isBroken) {
        this.isBroken = isBroken;

    }
    @Override
    public int brake(double speed) {
        double amountpieces = 1;
        amountpieces = speed*67;
        return (int)amountpieces;
    }
    @Override
    public boolean fix() {
        double isfixable = Math.random();
        if (isfixable>0.5) {
            isBroken = true;
        }
        return isBroken=false;
    }

}
