package ru.ssau.tk._division_._lr5_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr5_.functions.MathFunction;
import ru.ssau.tk._division_._lr5_.functions.SinFunction;

import static org.junit.jupiter.api.Assertions.*;

class MiddleSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        MathFunction sinFunction = new SinFunction();

        double step = 1e-3;
        MiddleSteppingDifferentialOperator operator = new MiddleSteppingDifferentialOperator(step);
        MathFunction sinFunctionD = operator.derive(sinFunction);

        assertEquals(1, sinFunctionD.apply(0), 1e-1);
        assertEquals(0, sinFunctionD.apply(Math.PI/2), 1e-1);
        assertEquals(-1, sinFunctionD.apply(Math.PI), 1e-1);
        assertEquals(0, sinFunctionD.apply(3*Math.PI/2), 1e-1);
    }
}