package ru.ssau.tk._division_._lr5_.io;

import ru.ssau.tk._division_._lr5_.functions.Point;
import ru.ssau.tk._division_._lr5_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

final class FunctionsIO {

    private FunctionsIO(){
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function)  throws IOException {
        DataOutputStream stream = new DataOutputStream(outputStream);
        stream.writeInt(function.getCount());

        for (Point point : function) {
            stream.writeDouble(point.x);
            stream.writeDouble(point.y);
        }
        stream.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        try {
            int count = Integer.parseInt(reader.readLine());
            double[] xValues = new double[count];
            double[] yValues = new double[count];
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                String[] parts = line.split(" ");
                try {
                    xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                    yValues[i] = numberFormat.parse(parts[1]).doubleValue();
                } catch (ParseException exception) {
                    throw new IOException(exception);
                }
            }
            return factory.create(xValues, yValues);
        } catch (NumberFormatException exception) {
            throw new IOException(exception);
        }
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream stream = new DataInputStream(inputStream);
        int count = stream.readInt();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; ++i) {
            xValues[i] = stream.readDouble();
            yValues[i] = stream.readDouble();
        }
        return factory.create(xValues, yValues);
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeObject(function);
        stream.flush();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }
}
