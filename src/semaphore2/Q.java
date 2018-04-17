package semaphore2;

import java.util.concurrent.Semaphore;

public class Q {

    private int n;

    //начать с недоступного семафора потребителя
    private static Semaphore semCon = new Semaphore(0);
    private static Semaphore semProd = new Semaphore(1);

    void get() {

        try {
            semCon.acquire();
        } catch (InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }

        System.out.println("Получено: " + n);
        semProd.release();

    }

    void put(int n) {

        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }

        this.n = n;
        System.out.println("Отправлено: " + n);
        semCon.release();

    }

}
