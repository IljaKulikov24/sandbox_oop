package ru.ssau.tk._division_._lr4_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosFunctionTest {

    @Test
    void apply() {

        MathFunction cos = new CosFunction();

        assertEquals(1.0, cos.apply(0.0), 1e-9);
        assertEquals(0.0, cos.apply(Math.PI / 2), 1e-9);
        assertEquals(-1.0, cos.apply(Math.PI), 1e-9);
        assertEquals(0.0, cos.apply(3 * Math.PI / 2), 1e-9);
    }
}