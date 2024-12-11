package ru.ssau.tk._division_._lr6_.io;

import ru.ssau.tk._division_._lr6_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr6_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        try(FileOutputStream outputStream = new FileOutputStream("output/serialized linked list functions.bin")){

            BufferedOutputStream stream = new BufferedOutputStream(outputStream);

            double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
            double[] yValues = {1.0, 4.0, 9.0, 16.0, 25.0};

            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues,yValues);

            TabulatedDifferentialOperator differentialOperator =new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

            TabulatedFunction diffFunc = differentialOperator.derive(function);
            TabulatedFunction diffFunc2 = differentialOperator.derive(diffFunc);

            FunctionsIO.serialize(stream,function);
            FunctionsIO.serialize(stream,diffFunc);
            FunctionsIO.serialize(stream,diffFunc2);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream("output/serialized linked list functions.bin")){
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));
            System.out.println(FunctionsIO.deserialize(bufferedInputStream));

        } catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }
}
