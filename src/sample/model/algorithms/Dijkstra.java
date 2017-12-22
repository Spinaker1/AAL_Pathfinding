package sample.model.algorithms;

import sample.Point;
import sample.model.Model;

public class Dijkstra extends AStar {

    @Override
    protected int findHeuristic(Point p1, Point p2) {
        return 0;
    }
}
