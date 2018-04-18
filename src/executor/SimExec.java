package executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimExec {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);

        CountDownLatch cdl1 = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);

        System.out.println("Запуск потоков");

        //запустить потоки исполнения
        es.execute(new MyThread("A", cdl1));
        es.execute(new MyThread("B", cdl2));
        es.execute(new MyThread("C", cdl3));
        es.execute(new MyThread("D", cdl4));

        try {
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        }

        es.shutdown();
        System.out.println("Завершение потоков");
    }
}
