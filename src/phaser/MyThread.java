package phaser;

import java.util.concurrent.Phaser;

//Поток исполнения, использующий синхронизатор фаз типа Phaser
public class MyThread implements Runnable{

    private Phaser phsr;
    private String name;

    public MyThread(Phaser phsr, String name) {
        this.phsr = phsr;
        this.name = name;

        phsr.register();
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println("Поток " + name + " начинает первую фазу");
        phsr.arriveAndAwaitAdvance();//известить о достижении фазы

        //Небольшая пауза, чтобы не нарушить порядок вывода.
        //Только для иллюстрации, но не обязательно для правильного функционирования синхронизатора фаз
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println("Поток " + name + " начинает вторую фазу");
        phsr.arriveAndAwaitAdvance();//известить о достижении фазы

        //Небольшая пауза, чтобы не нарушить порядок вывода.
        //Только для иллюстрации, но не обязательно для правильного функционирования синхронизатора фаз
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println("Поток " + name + " начинает третью фазу");
        phsr.arriveAndDeregister();//известить о достижении фазы
    }
}
