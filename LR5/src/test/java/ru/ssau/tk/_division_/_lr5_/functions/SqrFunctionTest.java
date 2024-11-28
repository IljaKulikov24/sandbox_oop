package ru.ssau.tk._division_._lr5_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void apply() {
        MathFunction sqr = new SqrFunction();

        assertEquals(0.0, sqr.apply(0.0), 1e-9);
        assertEquals(4.0, sqr.apply(2.0), 1e-9);
        assertEquals(4.0, sqr.apply(-2.0), 1e-9);
        assertEquals(0.01, sqr.apply(0.1), 1e-9);
    }
}