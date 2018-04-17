package semaphore2;

public class Consumer implements Runnable{

    private Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        for (int i=0; i<20; i++) q.get();
    }
}
