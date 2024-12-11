package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.*;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread readStream = new Thread(new ReadTask(function));
        Thread writeStream = new Thread(new WriteTask(function, 0.5));
        readStream.start();
        writeStream.start();
    }
}
