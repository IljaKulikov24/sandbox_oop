package ru.ssau.tk._division_._lr3_.functions;

import java.util.Random;

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
        Random RANDOM = new Random();
        return RANDOM.nextInt();
    }

    @Override
    public IdentityFunction clone() {
        return new IdentityFunction();
    }
}
