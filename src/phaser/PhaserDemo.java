package phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {

    public static void main(String[] args) {

        Phaser phsr = new Phaser(1);
        int curPhase;

        System.out.println("Запуск потоков");

        new MyThread(phsr, "A");
        new MyThread(phsr, "B");
        new MyThread(phsr, "C");

        //ожидать завершения всеми потоками исполнения первой фазы
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена");

        //ожидать завершения всеми потоками исполнения второй фазы
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена");

        //ожидать завершения всеми потоками исполнения третьей фазы
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Фаза " + curPhase + " завершена");

        //снять основной поток исполнения с регистрации
        phsr.arriveAndDeregister();

        if (phsr.isTerminated()) System.out.println("Синхронизатор фаз завершен");

    }
}
