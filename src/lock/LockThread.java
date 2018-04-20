package lock;

import java.util.concurrent.locks.ReentrantLock;

//Поток исполнения, инкрементирующий значение счетчика
public class LockThread implements Runnable {

    private String name;
    private ReentrantLock lock;

    public LockThread(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;

        new Thread(this).start();

    }

    @Override
    public void run() {

        System.out.println("Запуск потока" + name);

        try {
            //сначала заблокировать счетчик
            System.out.println("Поток " + name + " ожидает блокировки счетчика");
            lock.lock();
            System.out.println("Поток " + name + " блокирует счетчик");
            Shared.count++;
            System.out.println("Поток " + name + ": " + Shared.count);

            //а теперь переключение контекста, если это возможно
            System.out.println("Поток " + name + " ожидает");
            Thread.sleep(1000);

        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        } finally {
            //снять блокировку
            System.out.println("Поток " + name + " разблокирует счетчик");
            lock.unlock();
        }

    }
}
