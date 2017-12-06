package sample.model.algorithms;

import sample.Point;

public class SampleAlgorithm implements Algorithm {
    public void findPath() {

    }

    private class Vertex {
        Point point;
        int distance;

        Vertex(Point point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }
}
