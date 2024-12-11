package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public class ReadTask implements Runnable {

    private TabulatedFunction tabulatedFunction;

    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            System.out.println("After read: " + i +
                    " = %d, " + tabulatedFunction.getX(i) +
                    " = %f, " + tabulatedFunction.getY(i) + " = %f");
        }
    }
}
