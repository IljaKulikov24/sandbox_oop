package ru.ssau.tk._division_._lr3_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinFunctionTest {

    @Test
    void apply() {
        MathFunction sin = new SinFunction();

        assertEquals(0.0, sin.apply(0.0), 1e-9);
        assertEquals(1.0, sin.apply(Math.PI / 2), 1e-9);
        assertEquals(0.0, sin.apply(Math.PI), 1e-9);
        assertEquals(-1.0, sin.apply(3 * Math.PI / 2), 1e-9);
    }
}