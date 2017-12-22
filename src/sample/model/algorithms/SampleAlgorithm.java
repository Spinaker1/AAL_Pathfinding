package sample.model.algorithms;

import sample.Color;
import sample.Point;
import sample.model.Grid;

import java.util.LinkedList;

public class SampleAlgorithm implements Algorithm {

    public LinkedList<Point> findPath(Grid grid, Point start, Point finish, LinkedList<Point> usedPoints, boolean ignoreBlack) {
        LinkedList<Vertex> vertList = new LinkedList<Vertex>();
        vertList.push(new Vertex(start, 0,null));

        Vertex finishVert;
        LinkedList<Vertex> vertsToAdd = new LinkedList<Vertex>();

        main_loop:
        for (;;) {
            for (Vertex vert: vertList ) {
                vertsToAdd.push(new Vertex(new Point(vert.point.getX(), vert.point.getY() + 1), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX(), vert.point.getY() - 1), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX() + 1, vert.point.getY()), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX() - 1, vert.point.getY()), vert.distance + 1, vert));
            }

            small_loop:
            for (Vertex vert1: vertsToAdd ) {
                if (vert1.point.getX() < 0 || vert1.point.getX() >= grid.getWidth())
                    continue;
                if (vert1.point.getY() < 0 || vert1.point.getY() >= grid.getHeight())
                    continue;
                if (grid.tiles[vert1.point.getX()][vert1.point.getY()].getColor() == Color.BLACK)
                    continue;
                for (Vertex vert2: vertList)
                {
                    if (vert1.point.equals(vert2.point) && vert1.distance >= vert2.distance)
                        continue small_loop;
                }
                vertList.push(vert1);

                if (vert1.point.equals(finish))
                {
                    finishVert = vert1;
                    break main_loop;
                }
            }

            vertsToAdd.clear();
        }

        if (usedPoints != null) {
            for (Vertex vert1 : vertList) {
                if (!vert1.point.equals(finish) && !vert1.point.equals(start))
                    usedPoints.add(vert1.point);
            }
        }

        LinkedList<Point> shortestPath = new LinkedList<Point>();
        for (Vertex vertex = finishVert.predecessor; vertex.predecessor != null; vertex=vertex.predecessor) {
            shortestPath.add(vertex.point);
        }

        return shortestPath;
    }

    private class Vertex {
        Point point;
        int distance;
        Vertex predecessor;

        Vertex(Point point, int distance, Vertex predecessor) {
            this.point = point;
            this.distance = distance;
            this.predecessor = predecessor;
        }

        Vertex() {
        }
    }
}
