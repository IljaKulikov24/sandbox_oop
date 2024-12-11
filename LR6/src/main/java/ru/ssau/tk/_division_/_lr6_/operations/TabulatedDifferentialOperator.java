package ru.ssau.tk._division_._lr6_.operations;

import ru.ssau.tk._division_._lr6_.functions.Point;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr6_.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        int size = function.getCount();
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        //Первые n-1 точек
        for (int i = 0; i < size - 1; ++i) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }

        //Последняя точка
        xValues[size - 1] = points[size - 1].x;
        yValues[size - 1] = (points[size - 1].y - points[size - 2].y) / (points[size - 1].x - points[size - 2].x);

        return factory.create(xValues, yValues);

    }
}