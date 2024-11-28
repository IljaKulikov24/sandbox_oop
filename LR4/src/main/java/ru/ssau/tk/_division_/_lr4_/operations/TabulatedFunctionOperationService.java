package ru.ssau.tk._division_._lr4_.operations;

import ru.ssau.tk._division_._lr4_.exceptions.InconsistentFunctionsException;
import ru.ssau.tk._division_._lr4_.functions.Point;
import ru.ssau.tk._division_._lr4_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr4_.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int index = 0;
        for (Point point : tabulatedFunction) {
            points[index++] = point;
        }

        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount())
            throw new InconsistentFunctionsException("Кол-во точек различны");

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        int size = a.getCount();
        double[] xValues = new double[size];
        double[] yValues = new double[size];
        for (int i = 0; i < size; ++i) {
            if (pointsA[i].x != pointsB[i].x)
                throw new InconsistentFunctionsException("Абсциссы точек не равны");
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction addition(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtraction(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiplication(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}
