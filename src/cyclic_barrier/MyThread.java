package cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//Поток исполнения, использующий барьер типа CyclicBarrier
public class MyThread implements Runnable{

    private CyclicBarrier cbar;
    private String name;

    public MyThread(CyclicBarrier cbar, String name) {
        this.cbar = cbar;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println(name);
        try {
            cbar.await();
        }catch (BrokenBarrierException exc) {
            System.out.println(exc.getMessage());
        }catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
