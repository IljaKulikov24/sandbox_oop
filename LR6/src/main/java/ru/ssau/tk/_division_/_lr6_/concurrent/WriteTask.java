package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public class WriteTask implements Runnable {

    private final TabulatedFunction tabulatedFunction;
    private final double value;

    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            synchronized (tabulatedFunction) { // Синхронизация по объекту tabulatedFunction
                tabulatedFunction.setY(i, value);
                System.out.printf("Writing for index %d complete\n", i);
            }
        }
    }
}
