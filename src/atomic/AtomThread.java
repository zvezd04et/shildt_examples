package atomic;

//Поток исполнения, в котором инкрементируется значение счетчика
public class AtomThread implements Runnable {

    private String name;

    public AtomThread(String name) {
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println("Запуск потока " + name);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Поток " + name + " получено: " + Shared.ai.getAndSet(i));
        }

    }
}
