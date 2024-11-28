package ru.ssau.tk._division_._lr5_.functions;

//Интерфейс для математических функций.
public interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}