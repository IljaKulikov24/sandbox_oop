package ru.ssau.tk._division_._lr6_.concurrent;

import ru.ssau.tk._division_._lr6_.functions.Point;
import ru.ssau.tk._division_._lr6_.functions.TabulatedFunction;
import ru.ssau.tk._division_._lr6_.operations.TabulatedFunctionOperationService;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction tabulatedFunction;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public int getCount() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (tabulatedFunction) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.apply(x);
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (tabulatedFunction) {
            Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);

            return new Iterator<Point>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < getCount();
                }

                @Override
                public Point next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Point point = new Point(points[i].x, points[i].y);
                    ++i;
                    return point;
                }
            };
        }
    }
}
