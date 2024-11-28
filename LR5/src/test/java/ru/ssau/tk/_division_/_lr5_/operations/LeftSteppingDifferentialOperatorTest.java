package ru.ssau.tk._division_._lr5_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr5_.functions.MathFunction;
import ru.ssau.tk._division_._lr5_.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

class LeftSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        MathFunction sqrFunction = new SqrFunction();

        double step = 1e-3;
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(step);
        MathFunction sqrFunctionD = operator.derive(sqrFunction);

        assertEquals(2, sqrFunctionD.apply(1), 1e-1);
        assertEquals(20, sqrFunctionD.apply(10), 1e-1);
        assertEquals(0, sqrFunctionD.apply(0), 1e-1);
    }
}