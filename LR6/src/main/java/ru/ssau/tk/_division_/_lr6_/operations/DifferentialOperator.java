package ru.ssau.tk._division_._lr6_.operations;

import ru.ssau.tk._division_._lr6_.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);
}
