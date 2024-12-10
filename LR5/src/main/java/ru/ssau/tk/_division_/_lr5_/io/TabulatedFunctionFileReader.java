package ru.ssau.tk._division_._lr5_.io;

import ru.ssau.tk._division_._lr5_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr5_.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        try (BufferedReader readerArray = new BufferedReader(new FileReader("input/function.txt"));
             BufferedReader readerLinkedList = new BufferedReader(new FileReader("input/function.txt"))) {

            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(readerArray, new ArrayTabulatedFunctionFactory());
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(readerLinkedList, new LinkedListTabulatedFunctionFactory());

            System.out.println(arrayFunction);
            System.out.println(linkedListFunction);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
