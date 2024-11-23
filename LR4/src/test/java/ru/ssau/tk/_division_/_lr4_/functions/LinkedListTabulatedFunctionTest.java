package ru.ssau.tk._division_._lr4_.functions;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    @Test
    void testGettersAndSetY() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getX(0), 1e-9);
        assertEquals(4.0, function.getY(2), 1e-9);
        function.setY(2, 1.0);
        assertEquals(1.0, function.getY(2), 1e-9);
    }

    @Test
    void testIndexOfXAndOfY() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(2, function.indexOfX(3.0));
        assertEquals(-1, function.indexOfX(-1.0));

        assertEquals(2, function.indexOfY(4.0));
        assertEquals(-1, function.indexOfY(-1.0));
    }

    @Test
    void testLeftAndRightBound() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(1.0, function.leftBound(), 1e-9);
        assertEquals(3.0, function.rightBound(), 1e-9);
    }

    @Test
    public void testInterpolateAndExtrapolate() {
        MathFunction sqrFunction = new SqrFunction();
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);

        assertEquals(4.0, function.apply(2.0), 1e-9); // Точка из таблицы
        assertEquals(6.5, function.apply(2.5), 1e-9); // Интерполяция
        assertEquals(100.0, function.apply(10.0), 1e-9); // Правая граница
        assertEquals(0.0, function.apply(0.0), 1e-9); // Левая граница
        assertEquals(-1.0, function.apply(-1.0), 1e-9); // Экстраполяция влево
        assertEquals(119.0, function.apply(11.0), 1e-9); // Экстраполяция вправо
    }

    @Test
    void floorIndexOfX() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(1, function.floorIndexOfX(2.0), 1e-9);
        assertEquals(0, function.floorIndexOfX(1.5), 1e-9);
        assertEquals(1, function.floorIndexOfX(2.5), 1e-9);
        //assertEquals(0, function.floorIndexOfX(0.5), 1e-9);
        assertEquals(3, function.floorIndexOfX(3.5), 1e-9);
    }

    @Test
    void insert() {
        MathFunction sqrFunction = new SqrFunction();

        //insert с существующим "x"
        {
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(5, 100);
                assertEquals(100, function.getY(5));
                assertEquals(11, function.getCount());
            }
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(0, 100);
                assertEquals(100, function.getY(0));
                assertEquals(11, function.getCount());
            }
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(10, 100);
                assertEquals(100, function.getY(10));
                assertEquals(11, function.getCount());
            }
        }

        //"x" < leftBound
        {
            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(-1, 225);
            assertEquals(225, function.getY(0));
            assertEquals(0, function.getY(1));
            assertEquals(100, function.getY(11));
            assertEquals(12, function.getCount());
        }

        //"x" > rightBound
        {
            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
            function.insert(20, 400);
            assertEquals(400, function.getY(11));
            assertEquals(0, function.getY(0));
            assertEquals(25, function.getY(5));
            assertEquals(12, function.getCount());
        }

        //leftBound < "x" < rightBound
        {
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(0.1, 666);
                assertEquals(666, function.getY(1));
                assertEquals(100, function.getY(11));
                assertEquals(12, function.getCount());
            }
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(9.9, 666);
                assertEquals(666, function.getY(10));
                assertEquals(100, function.getY(11));
                assertEquals(12, function.getCount());
            }
            {
                LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 0, 10, 11);
                function.insert(5.5, 666);
                assertEquals(666, function.getY(6));
                assertEquals(100, function.getY(11));
                assertEquals(12, function.getCount());
            }
        }
    }

    @Test
    void remove() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);
        function.remove(0);
        assertEquals(3, function.getY(0));
        assertEquals(2, function.getCount());
    }

    @Test
    void testToString() {
        double[] xArray = {1.0, 2.0, 3.0};
        double[] yArray = {2.0, 3.0, 4.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);
        String points = "(1.0; 2.0)\n(2.0; 3.0)\n(3.0; 4.0)\n";
        String result = function.toString();
        assertEquals(points, result);
    }

    @Test
    void testEquals() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };
        double[] array = { 1.0 };

        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xArray, yArray);

        assertTrue(function1.equals(function1));
        assertFalse(function2.equals(array));
    }

    @Test
    void testHashCode() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xArray, yArray);

        assertEquals(function1.hashCode(), function2.hashCode());
    }

    @Test
    void testClone() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction clone = (LinkedListTabulatedFunction) function.clone();

        assertEquals(function.hashCode(), clone.hashCode());
        assertTrue(function.equals(clone));
        assertEquals(function.toString(), clone.toString());

        function.setY(2, 1.0);

        boolean flagHashCode = function.hashCode() == clone.hashCode();
        boolean flagEquals = function.equals(clone);
        boolean flagToString = function.toString().equals(clone.toString());

        assertFalse(flagHashCode);
        assertFalse(flagEquals);
        assertFalse(flagToString);

        System.out.println("function:\n" + function.toString());
        System.out.println("clone:\n" + clone.toString());
    }

    @Test
    void iteratorTest() {
        double[] xArray = { 1.0, 2.0, 3.0 };
        double[] yArray = { 2.0, 3.0, 4.0 };

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xArray, yArray);
        Iterator<Point> iterator = function.iterator();

        int index = 0;
        while (iterator.hasNext() && index != function.count) {
            Point point = iterator.next();
            assertEquals(function.getX(index), point.x);
            assertEquals(function.getY(index), point.y);
            ++index;
        }
        assertEquals(function.getCount(), index);
    }
}