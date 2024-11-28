package ru.ssau.tk._division_._lr5_.functions.factory;

import ru.ssau.tk._division_._lr5_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
