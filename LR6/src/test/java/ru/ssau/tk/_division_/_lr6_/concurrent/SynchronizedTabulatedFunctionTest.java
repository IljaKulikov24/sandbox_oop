package ru.ssau.tk._division_._lr6_.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk._division_._lr6_.functions.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {

    @Test
    void indexOfX() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(2, wrapper.indexOfX(3));
        assertEquals(-1, wrapper.indexOfX(0));
        assertEquals(-1, wrapper.indexOfX(3.5));
    }

    @Test
    void indexOfY() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(2, wrapper.indexOfY(4));
        assertEquals(-1, wrapper.indexOfY(0));
        assertEquals(-1, wrapper.indexOfY(3.5));
    }

    @Test
    void leftBound() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(1, wrapper.leftBound());
    }

    @Test
    void rightBound() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(5, wrapper.rightBound());
    }

    @Test
    void apply() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(4, wrapper.apply(3));
    }

    @Test
    void getCount() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(5, wrapper.getCount());
    }

    @Test
    void getX() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(4, wrapper.getX(3));
    }

    @Test
    void getY() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        assertEquals(5, wrapper.getY(3));
    }

    @Test
    void setY() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {2, 3, 4, 5, 6};

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(function);
        wrapper.setY(3, 666);
        assertEquals(666, wrapper.getY(3));
    }

    @Test
    void constructFunction() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 0, 5, 6);
        SynchronizedTabulatedFunction wrapper1 = new SynchronizedTabulatedFunction(function);
        boolean a = false;
        for (int i = 0; i < wrapper1.getCount(); i++) {
            if (wrapper1.getY(i) != i * i && wrapper1.getX(i) != i)
                a = true;
        }
        assertFalse(a);

        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(new SqrFunction(), 5, 0, 6);
        SynchronizedTabulatedFunction wrapper2 = new SynchronizedTabulatedFunction(func);
        boolean b = false;
        for (int i = 0; i < wrapper2.getCount(); i++) {
            if (wrapper2.getY(i) != i * i && wrapper2.getX(i) != i)
                b = true;
        }
        assertFalse(b);
    }

    @Test
    void iterator(){
        ArrayTabulatedFunction array = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        SynchronizedTabulatedFunction wrapper = new SynchronizedTabulatedFunction(array);
        Iterator<Point> iterator = wrapper.iterator();
        int i = 0;
        while(iterator.hasNext()){
            Point point = iterator.next();
            assertEquals(point.x, wrapper.getX(i));
            assertEquals(point.y, wrapper.getY(i++));
        }

        ArrayTabulatedFunction array2 = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
        SynchronizedTabulatedFunction wrapper2 = new SynchronizedTabulatedFunction(array2);
        int j = 0;
        for (Point point : wrapper2) {
            assertEquals(point.x, wrapper2.getX(j));
            assertEquals(point.y, wrapper2.getY(j++));
        }
    }

    @Test
    void iteratorSync()
    {
        SynchronizedTabulatedFunction arr = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(new double[]{1,2,3},new double[]{4,5,6}));
        Point[] points = new Point[3];
        int i =0;
        for(Point p: arr)
        {
            points[i] = p;
            ++i;
        }
        assertArrayEquals(points,new Point[]{new Point(1,4),new Point(2,5),new Point(3,6),});

        SynchronizedTabulatedFunction list = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(new double[]{1,2,3},new double[]{4,5,6}));
        Point[] points2 = new Point[3];
        int j =0;
        for(Point p: list)
        {
            points2[j] = p;
            ++j;
        }
        assertArrayEquals(points2,new Point[]{new Point(1,4),new Point(2,5),new Point(3,6),});

    }
}