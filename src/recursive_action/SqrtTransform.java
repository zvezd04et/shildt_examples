package recursive_action;

//Простой пример реализации стратегии "разделяй и властвуй".
//В данном примере применяется класс RecursiveAction

import java.util.concurrent.RecursiveAction;

//Класс ForkJoinTask преобразует (через класс RecursiveAction)
//значения элементов массива тип double в их квадратные корни
public class SqrtTransform extends RecursiveAction{

    //В данном примере пороговое значение произвольно устанавливается равным 1000.
    //В реальном коде его оптимальное значение может быть определено в результате
    //профилирования исполняющей системы или экспериментально
    private final int seqTheshold = 1000;

    //обрабатываемый массив
    private double[] data;

    //определить часть обрабатываемых данных
    private int start, end;

    public SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    //Этот метод выполняет параллельное вычисление
    @Override
    protected void compute() {

        //Если количество элементов меньше порогового значения,
        //выполнить дальнейшую обработку последовательно
        if ((end - start) < seqTheshold) {
            //преобразовать значение каждого элемента массива в его квадратный корень
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(i);
            }
        } else {
            //в противном случае продолжить разделение данных на меньшие части

            //найти середину
            int middle = (start + end) / 2;

            //запустить новые подзадачи на выполнение, используя разделение данных на меньшие части
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));

        }
    }
}
