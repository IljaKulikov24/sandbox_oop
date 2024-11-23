package ru.ssau.tk._division_._lr4_.operations;

import ru.ssau.tk._division_._lr4_.functions.Point;
import ru.ssau.tk._division_._lr4_.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int index = 0;
        for (Point point : tabulatedFunction) {
            points[index++] = point;
        }

        return points;
    }
}
