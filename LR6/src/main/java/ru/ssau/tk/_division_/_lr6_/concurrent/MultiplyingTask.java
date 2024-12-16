package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
            }
        }
        System.out.println(Thread.currentThread().getName() + " completed the task");
    }
}
