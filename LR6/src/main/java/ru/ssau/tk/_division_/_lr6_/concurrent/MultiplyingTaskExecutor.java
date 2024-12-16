package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            MultiplyingTask task = new MultiplyingTask(function);
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        while (!threads.isEmpty()) {
            threads.removeIf(thread -> !thread.isAlive());
        }
        System.out.println(function);
    }
}
