package recursive_action;

import java.util.concurrent.RecursiveAction;

public class Transform extends RecursiveAction{

    //порог последовательного выполнения
    private int seqThreshold;

    //обрабатываемый массив
    private double[] data;

    //определить часть обрабатываемых данных
    private int start, end;

    public Transform(double[] data, int start, int end, int seqThreshold) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.seqThreshold = seqThreshold;
    }

    @Override
    protected void compute() {

        //Если количество элементов меньше порогового значения,
        //выполнить дальнейшую обработку последовательно
        if ((end - start) < seqThreshold) {
            //элементу по четному индексу присваивается квадратный корень его первоначального значения,
            //а элементу по нечетному индексу - кубический корень его первоначального значения.
            //предназначено для демонстрации потребления времени ЦП
            for (int i = start; i < end; i++) {
                if ((i % 2 ) == 0)
                    data[i] = Math.sqrt(i);
                else
                    data[i] = Math.cbrt(i);
            }
        } else {
            //в противном случае продолжить разделение данных на меньшие части

            //найти середину
            int middle = (start + end) / 2;

            //запустить новые подзадачи на выполнение, используя разделение данных на меньшие части
            invokeAll(new Transform(data, start, middle, seqThreshold), new Transform(data, middle, end, seqThreshold));

        }

    }
}
