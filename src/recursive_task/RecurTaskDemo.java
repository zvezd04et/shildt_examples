package recursive_task;

import java.util.concurrent.ForkJoinPool;

//продемонстрировать параллельное выполнение
public class RecurTaskDemo {

    public static void main(String[] args) {

        //создать пул задач
        ForkJoinPool fjp = new ForkJoinPool();

        double[] nums = new double[5000];

        //присвоить некоторые значения
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) (((i % 2) == 0) ? i : -i);
        }

        Sum task = new Sum(nums, 0, nums.length);

        //запустить задачи типа ForkJoinTask
        //в данном случае метод invoke() возвращает результат
        double summation = fjp.invoke(task);

        System.out.println("Суммирование " + summation);

    }

}
