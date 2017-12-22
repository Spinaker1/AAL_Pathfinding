package sample.model.algorithms;

import sample.Point;
import sample.model.Grid;

import java.util.LinkedList;

public interface Algorithm {
    LinkedList<Point> findPath(Grid grid, Point start, Point finish, LinkedList<Point> usedPoints, boolean ignoreBlack);
}
