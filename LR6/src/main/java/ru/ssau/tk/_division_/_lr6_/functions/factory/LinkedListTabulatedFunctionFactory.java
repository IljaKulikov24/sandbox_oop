package ru.ssau.tk._division_._lr6_.functions.factory;

import ru.ssau.tk._division_._lr6_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}