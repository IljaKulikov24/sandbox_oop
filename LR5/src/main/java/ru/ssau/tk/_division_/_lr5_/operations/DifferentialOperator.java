package ru.ssau.tk._division_._lr5_.operations;

import ru.ssau.tk._division_._lr5_.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);
}
