package phaser_extend;

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

        while (!phsr.isTerminated()) {

            System.out.println("Поток " + name + " начинает фазу " + phsr.getPhase());
            phsr.arriveAndAwaitAdvance();//известить о достижении фазы

            //Небольшая пауза, чтобы не нарушить порядок вывода.
            //Только для иллюстрации, но не обязательно для правильного функционирования синхронизатора фаз
            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
            }

        }

    }
}
