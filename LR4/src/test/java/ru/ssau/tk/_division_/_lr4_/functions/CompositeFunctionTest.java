package ru.ssau.tk._division_._lr4_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    @Test
    void apply() {
        MathFunction identity = new IdentityFunction();
        MathFunction sqr = new SqrFunction();
        MathFunction sin = new SinFunction();
        MathFunction cos = new CosFunction();

        //Композиция sqr(sin(x))
        MathFunction sinSqr = new CompositeFunction(sqr, sin);
        assertEquals(sin.apply(sqr.apply(2)), sinSqr.apply(2.0), 1e-9);

        //Композиция sin(sqr(x))
        MathFunction sqrSin = new CompositeFunction(sin, sqr);
        assertEquals(sqr.apply(sin.apply(2)), sqrSin.apply(2.0), 1e-9);

        //Композиция sqr(cos(x))
        MathFunction cosSqr = new CompositeFunction(sqr, cos);
        assertEquals(cos.apply(sqr.apply(10)), cosSqr.apply(10.0), 1e-9);

        //Композиция sin(sqr(x))
        MathFunction sqrCos = new CompositeFunction(cos, sqr);
        assertEquals(sqr.apply(cos.apply(10)), sqrCos.apply(10.0), 1e-9);

        // Композиция сложной функции со сложной
        MathFunction composite = new CompositeFunction(sinSqr, sqrCos);
        assertEquals(sqrCos.apply(sinSqr.apply(666)), composite.apply(666), 1e-9);
    }
}