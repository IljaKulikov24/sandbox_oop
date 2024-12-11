package ru.ssau.tk._division_._lr6_.functions.factory;

import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
}
