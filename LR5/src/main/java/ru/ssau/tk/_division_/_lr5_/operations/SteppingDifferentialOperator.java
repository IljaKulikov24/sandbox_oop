package ru.ssau.tk._division_._lr5_.operations;

import ru.ssau.tk._division_._lr5_.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step))
            throw new IllegalArgumentException("Неверное значение шага");
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step))
            throw new IllegalArgumentException("Неверное значение шага");
        this.step = step;
    }
}
