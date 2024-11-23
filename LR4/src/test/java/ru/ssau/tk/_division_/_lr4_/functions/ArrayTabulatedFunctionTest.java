package ru.ssau.tk._division_._lr4_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void testGettersAndSetY() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);

        assertEquals(3, function.getCount());
        assertEquals(3.0, function.getX(2), 1e-9);
        assertEquals(4.0, function.getY(2), 1e-9);

        function.setY(2, 6.0);
        assertEquals(6.0, function.getY(2), 1e-9);
    }

    @Test
    void testLeftAndRightBound() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);

        assertEquals(1.0, function.leftBound(), 1e-9);
        assertEquals(3.0, function.rightBound(), 1e-9);
    }

    @Test
    void testIndexOfXAndOfY() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);

        assertEquals(1, function.indexOfX(2.0), 1e-9);
        assertEquals(-1, function.indexOfX(4.0), 1e-9);

        assertEquals(0, function.indexOfY(2.0), 1e-9);
        assertEquals(-1, function.indexOfY(1.0), 1e-9);
    }

    @Test
    void floorIndexOfX() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);

        assertEquals(1, function.floorIndexOfX(2.0), 1e-9);
        assertEquals(0, function.floorIndexOfX(1.5), 1e-9);
        assertEquals(1, function.floorIndexOfX(2.5), 1e-9);
        //assertEquals(0, function.floorIndexOfX(0.5), 1e-9);
        assertEquals(3, function.floorIndexOfX(3.5), 1e-9);
    }

    @Test
    public void testInterpolateAndExtrapolate() {
        MathFunction sqrFunction = new SqrFunction();
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);

        assertEquals(4.0, function.apply(2.0), 1e-9); // Точка из таблицы
        assertEquals(6.5, function.apply(2.5), 1e-9); // Интерполяция
        assertEquals(100.0, function.apply(10.0), 1e-9); // Правая граница
        assertEquals(0.0, function.apply(0.0), 1e-9); // Левая граница
        assertEquals(-1.0, function.apply(-1.0), 1e-9); // Экстраполяция влево
        assertEquals(119.0, function.apply(11.0), 1e-9); // Экстраполяция вправо
    }

    @Test
    void insert() {
        MathFunction sqrFunction = new SqrFunction();
        {
            //insert с существующим "x"
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(9, 82);
            assertEquals(82, function.getY(9));
            assertEquals(11, function.getCount());
        }
        {
            //"x" < leftBound
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(-1, 225);
            assertEquals(225, function.getY(0));
            assertEquals(100, function.getY(11));
            assertEquals(12, function.getCount());
        }
        {
            //"x" > rightBound
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(20, 400);
            assertEquals(400, function.getY(11));
            assertEquals(12, function.getCount());
        }
        {
            //leftBound < "x" < rightBound
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(0.1, 666);
            assertEquals(666, function.getY(1));
            assertEquals(100, function.getY(11));
            assertEquals(12, function.getCount());
        }
        {
            //leftBound < "x" < rightBound
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(9.9, 666);
            assertEquals(666, function.getY(10));
            assertEquals(100, function.getY(11));
            assertEquals(12, function.getCount());
        }
        {
            //leftBound < "x" < rightBound
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(5.5, 666);
            assertEquals(666, function.getY(6));
            assertEquals(100, function.getY(11));
            assertEquals(12, function.getCount());
        }
    }

    @Test
    void remove() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);
        function.remove(0);
        assertEquals(3, function.getY(0));
        assertEquals(2, function.getCount());
    }

    @Test
    void testToString() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);
        String points = "(1.0; 2.0)\n(2.0; 3.0)\n(3.0; 4.0)\n";
        String result = function.toString();
        assertEquals(points, result);
    }

    @Test
    void testEquals() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        double[] array = { 1.0 };

        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xArray, yArray);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xArray, yArray);

        assertTrue(function1.equals(function1));
        assertFalse(function2.equals(array));
    }

    @Test
    void testHashCode() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        ArrayTabulatedFunction function1 = new ArrayTabulatedFunction(xArray, yArray);
        ArrayTabulatedFunction function2 = new ArrayTabulatedFunction(xArray, yArray);

        assertEquals(function1.hashCode(), function2.hashCode());
    }

    @Test
    void testClone() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xArray, yArray);
        ArrayTabulatedFunction clone = (ArrayTabulatedFunction) function.clone();

        assertEquals(function.hashCode(), clone.hashCode());
        assertTrue(function.equals(clone));
        assertEquals(function.toString(), clone.toString());

        function.setY(0, 1.0);
        boolean flagHashCode = function.hashCode() == clone.hashCode();
        boolean flagEquals = function.equals(clone);
        boolean flagToString = function.toString().equals(clone.toString());

        assertFalse(flagHashCode);
        assertFalse(flagEquals);
        assertFalse(flagToString);

        System.out.println("function:\n" + function.toString());
        System.out.println("clone:\n" + clone.toString());
    }
}