package ru.ssau.tk._division_._lr5_.functions;

public class SqrFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}