package ru.ssau.tk._division_._lr4_.operations;

import ru.ssau.tk._division_._lr4_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.MathFunction;
import ru.ssau.tk._division_._lr4_.functions.Point;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x) - function.apply(x - step)) / step;
            }
        };
    }
}