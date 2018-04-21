package recursive_task;

import recursive_action.SqrtTransform;

import java.util.concurrent.RecursiveTask;

//Класс RecursiveTask, используемый для вычисления суммы значений элементов в массиве double
public class Sum  extends RecursiveTask<Double>{

    //пороговое значение последовательного выполнения
    private final int seqThresHold = 500;

    //обрабатываемый массив
    private double[] data;

    //определить часть обрабатываемых данных
    private int start, end;

    public Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {

        double sum = 0;

        //Если количество элементов меньше порогового значения,
        //выполнить дальнейшую обработку последовательно
        if ((end - start) < seqThresHold) {
            //преобразовать значение каждого элемента массива в его квадратный корень
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            //в противном случае продолжить разделение данных на меньшие части

            //найти середину
            int middle = (start + end) / 2;

            //запустить новые подзадачи на выполнение, используя разделенные на части данные
            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle, end);

            //запустить каждую задачу путем разветвления
            subTaskA.fork();
            subTaskB.fork();

            //ожидать завершения подзадач и накопить результаты
            sum = subTaskA.join() + subTaskB.join();
        }

        return sum;
    }
}
