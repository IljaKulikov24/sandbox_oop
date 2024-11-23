package ru.ssau.tk._division_._lr4_.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr4_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionFactoryTest {

    @Test
    void createTest() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        ArrayTabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();
        assertFalse(functionFactory.create(xArray, yArray) instanceof LinkedListTabulatedFunction);
        assertTrue(functionFactory.create(xArray, yArray) instanceof ArrayTabulatedFunction);
    }
}