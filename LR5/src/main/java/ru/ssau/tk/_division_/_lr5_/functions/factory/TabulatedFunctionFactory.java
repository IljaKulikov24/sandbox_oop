package ru.ssau.tk._division_._lr5_.functions.factory;

import ru.ssau.tk._division_._lr5_.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    public TabulatedFunction create(double[] xValues, double[] yValues);
}
