package ru.ssau.tk._division_._lr6_.io;

import ru.ssau.tk._division_._lr6_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (FileOutputStream outputStreamFirst = new FileOutputStream("output/array function.bin");
             FileOutputStream outputStreamSecond = new FileOutputStream("output/linked list function.bin")) {

            BufferedOutputStream streamFirst = new BufferedOutputStream(outputStreamFirst);
            BufferedOutputStream streamSecond = new BufferedOutputStream(outputStreamSecond);

            double[] ArrayX = {1, 2, 3};
            double[] ArrayY = {2, 3, 4};
            ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(ArrayX, ArrayY);
            LinkedListTabulatedFunction LinkedListFunction = new LinkedListTabulatedFunction(ArrayX, ArrayY);

            FunctionsIO.writeTabulatedFunction(streamFirst, arrayFunction);
            FunctionsIO.writeTabulatedFunction(streamSecond, LinkedListFunction);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
