package callable;

import java.util.concurrent.Callable;

public class Hypot implements Callable<Double> {

    private double side1, side2;

    public Hypot(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public Double call() {
        return Math.sqrt((side1*side1) + (side2*side2));
    }

}
