package ru.ssau.tk._division_._lr4_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr4_.functions.CosFunction;
import ru.ssau.tk._division_._lr4_.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class RightSteppingDifferentialOperatorTest {

    @Test
    void derive() {
        MathFunction cosFunction = new CosFunction();

        double step = 1e-3;
        LeftSteppingDifferentialOperator operator = new LeftSteppingDifferentialOperator(step);
        MathFunction cosFunctionD = operator.derive(cosFunction);

        assertEquals(0, cosFunctionD.apply(0), 1e-1);
        assertEquals(-1, cosFunctionD.apply(Math.PI/2), 1e-1);
        assertEquals(0, cosFunctionD.apply(Math.PI), 1e-1);
        assertEquals(1, cosFunctionD.apply(3*Math.PI/2), 1e-1);
    }
}