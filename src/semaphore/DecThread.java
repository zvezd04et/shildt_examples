package semaphore;

import java.util.concurrent.Semaphore;

//Поток исполнения, уменьшающй значение счетчика на единицу
public class DecThread implements Runnable{

    private String name;
    private Semaphore sem;

    public DecThread(Semaphore sem, String name) {

        this.name = name;
        this.sem = sem;

        new Thread(this).start();

    }

    @Override
    public void run() {

        System.out.println("Запуск потока " + name);

        try {
            // сначала получить разрешение
            System.out.println("Поток " + name + " ождает разрешения");
            sem.acquire();

            System.out.println("Поток " + name + " полуает разрешение");
            // а теперь получить доступ к общему ресурсу
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + " : " + Shared.count);
            }

            //разрешить , если возможо, переклчение контекста
            Thread.sleep(10);
        } catch (InterruptedException exc) {
            System.out.println(exc.toString());
        }

        //освободить разрешение
        System.out.println("Поток " + name + " освобождает разрешение");
        sem.release();

    }
}
