package callable;

import java.util.concurrent.Callable;

public class Factorial implements Callable<Integer> {

    private int stop;

    public Factorial(int stop) {
        this.stop = stop;
    }

    @Override
    public Integer call() {
        int fact = 1;
        for (int i = 2 ; i <= stop; i++) {
            fact *= i;
        }
        return fact;
    }

}
