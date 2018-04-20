package callable;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> f1;
        Future<Double>  f2;
        Future<Integer> f3;

        System.out.println("Запуск");

        f1 = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3,4));
        f3 = es.submit(new Factorial(5));

        try {
            System.out.println(f1.get(10, TimeUnit.MILLISECONDS));
            System.out.println(f2.get(10, TimeUnit.MILLISECONDS));
            System.out.println(f3.get(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
        } catch (ExecutionException exc) {
            System.out.println(exc.getMessage());
        } catch (TimeoutException exc) {
            System.out.println(exc.getMessage());
        }

        es.shutdown();
        System.out.println("Завершение");

    }
}
