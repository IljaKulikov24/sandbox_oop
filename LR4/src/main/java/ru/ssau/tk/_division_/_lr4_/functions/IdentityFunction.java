package ru.ssau.tk._division_._lr4_.functions;

public class  IdentityFunction implements MathFunction, Cloneable {

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "(IdentityFunction) Объекты этого класса выполняют тождественное преобразование";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof IdentityFunction;
    }

    @Override
    public int hashCode() {
        return  23;
    }

    @Override
    public IdentityFunction clone() {
        return new IdentityFunction();
    }
}
