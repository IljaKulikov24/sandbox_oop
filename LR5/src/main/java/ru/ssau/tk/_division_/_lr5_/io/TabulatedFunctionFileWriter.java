package ru.ssau.tk._division_._lr5_.io;

import ru.ssau.tk._division_._lr5_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {
        double[] ArrayX = { 0, 1, 2 };
        double[] ArrayY = { 1, 2, 3 };

        try (BufferedWriter arrayWriter = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter linkedListWriter = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {

            FunctionsIO.writeTabulatedFunction(arrayWriter, new ArrayTabulatedFunction(ArrayX, ArrayY));
            FunctionsIO.writeTabulatedFunction(linkedListWriter, new LinkedListTabulatedFunction(ArrayX, ArrayY));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
