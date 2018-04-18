package exchanger;

import java.util.concurrent.Exchanger;

//Поток типа Thread, формирующий символьную строку
public class MakeString implements Runnable {

    private Exchanger<String> ex;
    private String str;

    public MakeString(Exchanger<String> ex) {
        this.ex = ex;
        str = new String();
        new Thread(this).start();
    }

    @Override
    public void run() {

        char ch = 'A';
        for (int i = 0; i < 4; i++) {

            //заполнить буфер
            for (int j = 0; j < 5; j++) {
                str += (char) ch++;
            }

            try {
                //обменять заполненый буфер на пустой
                str = ex.exchange(str);
            }
            catch (InterruptedException exc) {
                System.out.println(exc.getMessage());
            }

        }
    }
}
