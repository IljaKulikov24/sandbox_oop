package ru.ssau.tk._division_._lr4_.operations;

import ru.ssau.tk._division_._lr4_.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);
}
