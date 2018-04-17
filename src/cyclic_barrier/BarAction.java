package cyclic_barrier;

//Объект этого класса вызывается по достижении барьера типа CyclicBarrier
public class BarAction implements Runnable{

    @Override
    public void run() {
        System.out.println("Барьер достигнут!");
    }

}
