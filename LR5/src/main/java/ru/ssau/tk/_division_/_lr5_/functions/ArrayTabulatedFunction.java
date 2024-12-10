package ru.ssau.tk._division_._lr5_.functions;

import ru.ssau.tk._division_._lr5_.exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    @Serial
    private static final long serialVersionUID = 5299736339661586895L;

    private double[] xValues;
    private double[] yValues;

    //Первый конструктор
    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        if (xValues.length < 2) throw new IllegalArgumentException("Количество точек должно быть не меньше двух");
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = xValues.length;
    }

    //Второй конструктор
    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Количество точек должно быть не меньше двух");

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        this.xValues = new double[count];
        this.yValues = new double[count];
        this.count = count;

        //учтен случай: xFrom = xTo
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; ++i) {
            xValues[i] = xFrom + step * i;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Вызван несуществующий индекс");
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Вызван несуществующий индекс");
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Вызван несуществующий индекс");
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; ++i) {
            if (xValues[i] == x) return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; ++i) {
            if (yValues[i] == y) return i;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) throw new IllegalArgumentException("Значение аргумента меньше левой границы");
        if (indexOfX(x) != -1) return indexOfX(x);
        if (x > xValues[count - 1]) return count;
        int index = 0;
        for (int i = 1; i < count; ++i) {
            if (xValues[i] > x) {
                index = i - 1;
                break;
            }
        }
        return index;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < getX(floorIndex) || x > getX(floorIndex + 1))
            throw new InterpolationException("Абсцисса вне интервала интерполирования");
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public void insert(double x, double y) {
        int index = 0;
        int oldBound = count;
        while (index != oldBound && x > getX(index))
            ++index;
        if (index < xValues.length) {
            if (xValues[index] == x) {
                yValues[index] = y;
                return;
            }
        }
        ++count;
        if (oldBound + 1 >= xValues.length) {
            double[] newXValues = new double[xValues.length * 2];
            double[] newYValues = new double[xValues.length * 2];
            System.arraycopy(xValues, 0, newXValues, 0, xValues.length);
            System.arraycopy(yValues, 0, newYValues, 0, yValues.length);
            xValues = newXValues;
            yValues = newYValues;
        }
        if (index == oldBound) {
            xValues[index] = x;
            yValues[index] = y;
            return;
        }
        for (int i = oldBound; i >= index; --i) {
            xValues[i + 1] = xValues[i];
            yValues[i + 1] = yValues[i];
        }
        xValues[index] = x;
        yValues[index] = y;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) throw new IllegalArgumentException("Вызван несуществующий индекс");

        double[] newXValues = new double[count - 1];
        double[] newYValues = new double[count - 1];

        for (int i = 0; i < index; ++i) {
            newXValues[i] = xValues[i];
            newYValues[i] = yValues[i];
        }
        for (int i = index; i < count - 1; ++i) {
            newXValues[i] = xValues[i + 1];
            newYValues[i] = yValues[i + 1];
        }

        --count;
        this.xValues = newXValues;
        this.yValues = newYValues;
    }

    /*Наследуется от AbstractTabulatedFunction
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            result.append('(').append(xValues[i]).append("; ")
                    .append(yValues[i]).append(")\n");
        }
        return result.toString();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTabulatedFunction)) return false;
        ArrayTabulatedFunction other = (ArrayTabulatedFunction) o;

        return Arrays.equals(this.xValues, other.xValues)
                && Arrays.equals(this.yValues, other.yValues);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (int i = 0; i < count; ++i) {
            hash *= (int) (xValues[i] + yValues[i]);
        }
        return hash;
    }

    @Override
    public Object clone() {
        double[] xArray = Arrays.copyOf(this.xValues, count);
        double[] yArray = Arrays.copyOf(this.yValues, count);
        return new ArrayTabulatedFunction(xArray, yArray);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex != count;
            }

            @Override
            public Point next() {
                if (!hasNext()) throw new NoSuchElementException();
                Point point = new Point(xValues[currentIndex], yValues[currentIndex]);
                ++currentIndex;
                return point;
            }
        };
    }
}
