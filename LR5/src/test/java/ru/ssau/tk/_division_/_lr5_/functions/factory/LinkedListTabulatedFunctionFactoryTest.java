package ru.ssau.tk._division_._lr5_.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr5_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr5_.functions.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {

    @Test
    void createTest() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        LinkedListTabulatedFunctionFactory functionFactory = new LinkedListTabulatedFunctionFactory();
        assertTrue(functionFactory.create(xArray, yArray) instanceof LinkedListTabulatedFunction);
        assertFalse(functionFactory.create(xArray, yArray) instanceof ArrayTabulatedFunction);
    }
}