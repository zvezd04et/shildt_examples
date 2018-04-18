package executor;

import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {

    private String name;
    private CountDownLatch latch;

    public MyThread(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;

        new Thread(this);
    }

    @Override
    public void run() {


        for (int i = 0; i < 5; i++) {
            System.out.println(name + ": " + i);
            latch.countDown();
        }
    }
}
