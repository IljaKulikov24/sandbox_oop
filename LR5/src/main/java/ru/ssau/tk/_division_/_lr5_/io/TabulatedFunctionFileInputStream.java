package ru.ssau.tk._division_._lr5_.io;

import ru.ssau.tk._division_._lr5_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr5_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr5_.operations.TabulatedDifferentialOperator;

import java.io.*;

import static ru.ssau.tk._division_._lr5_.io.FunctionsIO.readTabulatedFunction;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("input/binary function.bin")) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(function);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.out.println("Введите размер и значения функции");
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);

        try {
            TabulatedFunction consoleFunction = readTabulatedFunction(bufferedReader, new LinkedListTabulatedFunctionFactory());
            TabulatedFunction derivative = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory()).derive(consoleFunction);
            System.out.println(derivative);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
