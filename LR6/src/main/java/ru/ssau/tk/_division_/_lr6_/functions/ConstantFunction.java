package ru.ssau.tk._division_._lr6_.functions;

public class ConstantFunction implements MathFunction {

    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    @Override
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}