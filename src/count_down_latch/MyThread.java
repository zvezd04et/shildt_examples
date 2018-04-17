package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable{

    private CountDownLatch latch;

    public MyThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(i);
                Thread.sleep(100);
                latch.countDown();
            } catch (InterruptedException exc) {
                System.out.println(exc.toString());
            }
        }
    }
}
