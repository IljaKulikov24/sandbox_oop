package ru.ssau.tk._division_._lr6_.io;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr6_.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
