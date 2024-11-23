package ru.ssau.tk._division_._lr4_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    @Test
    void testApply() {
        MathFunction identity = new IdentityFunction();

        assertEquals(0.0, identity.apply(0.0), 1e-9);
        assertEquals(5.0, identity.apply(5.0), 1e-9);
        assertEquals(-3.14, identity.apply(-3.14), 1e-9);
    }

    @Test
    void testToString() {
        MathFunction identity = new IdentityFunction();
        assertEquals("(IdentityFunction) Объекты этого класса выполняют тождественное преобразование", identity.toString());
    }

    @Test
    void testEquals() {
        MathFunction identity1 = new IdentityFunction();
        MathFunction identity2 = new IdentityFunction();
        MathFunction function = new SqrFunction();

        assertTrue(identity1.equals(identity2));
        assertTrue(identity1.equals(identity1));
        assertFalse(identity1.equals(function));
        assertFalse(identity1.equals(null));
    }

    @Test
    void testHashCode() {
        MathFunction identity = new IdentityFunction();

        assertEquals(23, identity.hashCode());
    }

    @Test
    void testClone() {
        IdentityFunction original = new IdentityFunction();
        IdentityFunction cloned = original.clone();

        assertEquals(original, cloned);
    }
}