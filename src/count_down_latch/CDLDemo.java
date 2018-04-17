package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {

    public static void main(String[] args) {

        CountDownLatch cdl = new CountDownLatch(5);

        System.out.println("Запуск потока исполнения");

        new MyThread(cdl);

        try {
            cdl.await();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Завершение потока исполнения");
    }
}
