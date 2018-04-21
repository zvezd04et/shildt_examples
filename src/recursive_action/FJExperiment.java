package recursive_action;

import java.util.concurrent.ForkJoinPool;

public class FJExperiment {

    public static void main(String[] args) {

        int pLevel;
        int threshold;

        if (args.length != 2) {
            System.out.println("Не задан уровень параллелизма и порог последовательной обработки");
            return;
        }

        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        //эти переменные используются для измерения времени выполнения задачи
        long beginT, endT;

        //создать пул задач с установкой уровня параллелизма
        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        double[] nums = new double[1000000];

        //присвоить некоторые значения
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        Transform task = new Transform(nums, 0, nums.length, threshold);

        //начать измерение выполнения задачи
        beginT = System.nanoTime();

        //запустить главную задачу типа ForkJoinTask
        fjp.invoke(task);

        //завершить измерение времени выполнения задачи
        endT = System.nanoTime();

        System.out.println("Уровень параллелизма: " + pLevel);
        System.out.println("Порого последовательной обработки: " + threshold);
        System.out.println("Истекшее время: " + (endT - beginT) + " нс");
        System.out.println();

    }
}
