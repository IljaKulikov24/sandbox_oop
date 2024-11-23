package ru.ssau.tk._division_._lr4_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr4_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._division_._lr4_.functions.Point;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPoints() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        {
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);
            Point[] points = TabulatedFunctionOperationService.asPoints(function);

            for (int i = 0; i < xArray.length; ++i) {
                assertEquals(function.getX(i), points[i].x);
                assertEquals(function.getY(i), points[i].y);
            }
        }
        {
            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

            Point[] points = TabulatedFunctionOperationService.asPoints(function);

            for (int i = 0; i < xArray.length; ++i) {
                assertEquals(function.getX(i), points[i].x);
                assertEquals(function.getY(i), points[i].y);
            }
        }
    }
}