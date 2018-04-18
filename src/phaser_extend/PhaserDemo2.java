package phaser_extend;

import phaser.MyThread;

import java.util.concurrent.Phaser;

public class PhaserDemo2 {

    public static void main(String[] args) {

        Phaser phsr = new MyPhaser(1, 4);

        System.out.println("Запуск потоков\n");

        new MyThread(phsr, "A");
        new MyThread(phsr, "B");
        new MyThread(phsr, "C");

        //ожидать завершения определенного количества фаз
        while (!phsr.isTerminated()){
            phsr.arriveAndAwaitAdvance();
        }

        System.out.println("Синхронизатор фаз завершен");

    }
}
