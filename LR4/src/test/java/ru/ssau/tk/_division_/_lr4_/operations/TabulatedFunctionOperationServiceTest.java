package ru.ssau.tk._division_._lr4_.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr4_.functions.*;
import ru.ssau.tk._division_._lr4_.functions.factory.ArrayTabulatedFunctionFactory;

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

    @Test
    void doOperation() {
        {
            double[] xArray = { 1.0, 2.0, 3.0 };

            double[] yArrayA = { 2.0, 3.0, 4.0 };
            ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xArray, yArrayA);
            double[] yArrayB = { 3.0, 4.0, 5.0 };
            ArrayTabulatedFunction functionB = new ArrayTabulatedFunction(xArray, yArrayB);

            ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

            TabulatedFunction result = service.addition(functionA, functionB);
            assertEquals(5, result.getY(0));
            assertEquals(7, result.getY(1));
            assertEquals(9, result.getY(2));
        }
        {
            double[] xArray = { 1.0, 2.0, 3.0 };

            double[] yArrayA = { 2.0, 3.0, 4.0 };
            ArrayTabulatedFunction functionA = new ArrayTabulatedFunction(xArray, yArrayA);
            double[] yArrayB = { 3.0, 4.0, 5.0 };
            LinkedListTabulatedFunction functionB = new LinkedListTabulatedFunction(xArray, yArrayB);

            ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);

            TabulatedFunction result = service.subtraction(functionA, functionB);
            assertEquals(-1, result.getY(0));
            assertEquals(-1, result.getY(1));
            assertEquals(-1, result.getY(2));
        }
    }
}