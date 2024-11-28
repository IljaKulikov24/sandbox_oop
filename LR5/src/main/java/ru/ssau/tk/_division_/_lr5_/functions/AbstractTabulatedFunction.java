package ru.ssau.tk._division_._lr5_.functions;

import ru.ssau.tk._division_._lr5_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._division_._lr5_.exceptions.DifferentLengthOfArraysException;

//Абстрактный класс для табулированных функций.
public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                int floorIndex = floorIndexOfX(x);
                return interpolate(x, floorIndex);
            }
        }
    }

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length)
            throw new DifferentLengthOfArraysException("Количество значений абсцисс и ординат не совпадают");
    }

    static void checkSorted(double[] xValues) {
        int index = 0;
        while (index < xValues.length - 1) {
            if (xValues[index] > xValues[index++ + 1]) {
                throw new ArrayIsNotSortedException("Абсциссы неупорядоченны");
            }
        }
    }
}