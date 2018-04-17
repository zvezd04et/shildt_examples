package cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

public class BarDemo {

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");

    }
}
