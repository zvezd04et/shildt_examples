package phaser_extend;

import java.util.concurrent.Phaser;

//Расширить класс MyPhaser, чтобы выполнить только
//определенное количестов фаз
public class MyPhaser extends Phaser {

    private int numPhases;

    public MyPhaser(int parties, int phasesCount) {
        super(parties);
        numPhases = phasesCount - 1;
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        //Следующий оператор println() требуется только для целей иллюстрации. Как правило, метод onAdvance()
        //не отображает выводимые данные
        System.out.println("Фаза " + phase + " завершена.\n");

        //возвратить логическое true, если все фазы завершены
        //в противном случае возвратить логическое false
        return (phase == numPhases || registeredParties == 0);

    }
}
