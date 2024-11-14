package ru.ssau.tk._division_._lr3_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    @Test
    void apply() {
        MathFunction identity = new IdentityFunction();

        assertEquals(0.0, identity.apply(0.0), 1e-9);
        assertEquals(5.0, identity.apply(5.0), 1e-9);
        assertEquals(-3.14, identity.apply(-3.14), 1e-9);
    }
}