package exchanger;

import java.util.concurrent.Exchanger;

public class UseString implements Runnable {

    private Exchanger<String> ex;
    private String str;

    public UseString(Exchanger<String> ex) {
        this.ex = ex;
        new Thread(this).start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 4; i++) {
            try {
                //обменять пустой буфер на заполненный
                str = ex.exchange(new String());
                System.out.println("Получено: " + str);
            } catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }
}
