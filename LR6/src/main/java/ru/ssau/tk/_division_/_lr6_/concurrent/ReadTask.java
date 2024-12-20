package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public class ReadTask implements Runnable {

    private final TabulatedFunction tabulatedFunction;

    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            synchronized (tabulatedFunction) { // Синхронизация по объекту tabulatedFunction
                System.out.printf("After read: i = %d, x = %f, y = %f\n", i, tabulatedFunction.getX(i), tabulatedFunction.getY(i));
            }
        }
    }
}
