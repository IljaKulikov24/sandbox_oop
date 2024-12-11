package ru.ssau.tk._division_._lr6_.io;

import ru.ssau.tk._division_._lr6_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.SqrFunction;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"));
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            ArrayTabulatedFunction originalFunction = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);
            TabulatedFunction firstDerivative = new TabulatedDifferentialOperator().derive(originalFunction);
            TabulatedFunction secondDerivative = new TabulatedDifferentialOperator().derive(firstDerivative);

            oos.writeObject(originalFunction);
            oos.writeObject(firstDerivative);
            oos.writeObject(secondDerivative);

            oos.flush();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"));
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            TabulatedFunction deserializedOriginal = (TabulatedFunction) ois.readObject();
            TabulatedFunction deserializedFirstDerivative = (TabulatedFunction) ois.readObject();
            TabulatedFunction deserializedSecondDerivative = (TabulatedFunction) ois.readObject();

            System.out.println(deserializedOriginal);
            System.out.println(deserializedFirstDerivative);
            System.out.println(deserializedSecondDerivative);

        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
