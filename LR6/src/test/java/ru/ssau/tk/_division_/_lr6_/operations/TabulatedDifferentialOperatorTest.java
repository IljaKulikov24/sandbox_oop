package ru.ssau.tk._division_._lr6_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._division_._lr6_.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {

    @Test
    void deriveLinkedList() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 6.0, 9.0 };

        TabulatedFunction list = new LinkedListTabulatedFunctionFactory().create(xArray, yArray);
        TabulatedDifferentialOperator DList = new TabulatedDifferentialOperator();
        TabulatedFunction ListD = DList.derive(list);

        assertEquals(4, ListD.getY(0));
        assertEquals(3, ListD.getY(1));
        assertEquals(3, ListD.getY(2));
    }

    @Test
    void deriveArray() {
        double[] xArray = { 1.0, 6.0, 11.0 };
        double[] yArray = { 2.0, 14.0, 42.0 };

        TabulatedFunction array = new ArrayTabulatedFunctionFactory().create(xArray, yArray);
        TabulatedDifferentialOperator DArray = new TabulatedDifferentialOperator();
        TabulatedFunction ArrayD = DArray.derive(array);

        assertEquals(2.4, ArrayD.getY(0));
        assertEquals(5.6, ArrayD.getY(1));
        assertEquals(5.6, ArrayD.getY(2));
    }
}