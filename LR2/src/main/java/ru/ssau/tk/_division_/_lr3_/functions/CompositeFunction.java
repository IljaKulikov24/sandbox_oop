package ru.ssau.tk._division_._lr3_.functions;

//Класс для композиции двух функций.
public class CompositeFunction implements  MathFunction {

    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));
    }
}
