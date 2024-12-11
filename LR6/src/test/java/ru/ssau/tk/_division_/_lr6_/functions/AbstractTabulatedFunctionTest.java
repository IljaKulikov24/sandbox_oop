package ru.ssau.tk._division_._lr6_.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr6_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._division_._lr6_.exceptions.DifferentLengthOfArraysException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTabulatedFunctionTest {

    @Test
    void testCheckLengthIsTheSame() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0};

        assertThrows(DifferentLengthOfArraysException.class, () -> {
            AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        });

        double[] correctYValues = {4.0, 5.0, 6.0};
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, correctYValues);
    }

    @Test
    void testCheckSorted() {
        double[] unsortedXValues = {1.0, 3.0, 2.0};
        double[] sortedXValues = {1.0, 2.0, 3.0};

        assertThrows(ArrayIsNotSortedException.class, () -> {
            AbstractTabulatedFunction.checkSorted(unsortedXValues);
        });

        AbstractTabulatedFunction.checkSorted(sortedXValues);
    }

    @Test
    void testToString() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 8.0};

        LinkedListTabulatedFunction firstFunction = new LinkedListTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction secondFunction = new ArrayTabulatedFunction(xValues, yValues);

        String firstTestStr = "LinkedListTabulatedFunction size = 3\n[1.0; 4.0]\n[2.0; 5.0]\n[3.0; 8.0]\n";
        String secondTestStr = "ArrayTabulatedFunction size = 3\n[1.0; 4.0]\n[2.0; 5.0]\n[3.0; 8.0]\n";

        assertEquals(firstTestStr, firstFunction.toString());
        assertEquals(secondTestStr, secondFunction.toString());
    }
}