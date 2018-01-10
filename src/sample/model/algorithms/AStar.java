package sample.model.algorithms;

import sample.Color;
import sample.Point;
import sample.model.Grid;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar implements Algorithm {
    private Point finish;

    @Override
    public LinkedList<Point> findPath(Grid grid, Point start, Point finish, LinkedList<Point> usedPoints, boolean ignoreBlack) {
        this.finish = finish;
        Vertex finishVert = null;

        Comparator<Vertex> comparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return (int) (o1.fDistance - o2.fDistance);
            }
        };

        PriorityQueue<Vertex> openSet = new PriorityQueue<>(1,comparator);
        PriorityQueue<Vertex> closeSet = new PriorityQueue<>(1,comparator);
        LinkedList<Vertex> vertsToAdd = new LinkedList<Vertex>();

        openSet.add(new Vertex(start,0,null));

        for (;;) {
            if (ignoreBlack == false && openSet.size() == 0) {
                break;
            }

            Vertex current = openSet.poll();

            if (current.point.equals(finish)) {
                finishVert = current;
                break;
            }

            closeSet.add(current);

            vertsToAdd.push(new Vertex(new Point(current.point.getX(), current.point.getY() + 1)));
            vertsToAdd.push(new Vertex(new Point(current.point.getX(), current.point.getY() - 1)));
            vertsToAdd.push(new Vertex(new Point(current.point.getX() + 1, current.point.getY())));
            vertsToAdd.push(new Vertex(new Point(current.point.getX() - 1, current.point.getY())));

            main_loop:
            for (Iterator<Vertex> iter = vertsToAdd.iterator(); iter.hasNext(); ) {
                Vertex vert = (Vertex) iter.next();

                if (vert.point.getX() < 0 || vert.point.getX() >= grid.getWidth())
                    continue;
                if (vert.point.getY() < 0 || vert.point.getY() >= grid.getHeight())
                    continue;
                if (ignoreBlack == false && grid.tiles[vert.point.getX()][vert.point.getY()].getColor() == Color.BLACK)
                    continue;

                if (closeSet.contains(vert)) {
                    continue main_loop;
                }

                if (openSet.contains(vert)) {
                    continue main_loop;
                }

                if (current.gDistance+1 >= vert.gDistance) {
                    openSet.add(vert);
                    continue;
                }

                vert.predecessor = current;
                vert.gDistance = current.gDistance+1;
                vert.fDistance = vert.gDistance+findHeuristic(vert.point,finish);
                openSet.add(vert);
            }

            vertsToAdd.clear();
        }

        if (usedPoints != null) {
            for (Vertex vert1 : closeSet) {
                if (!vert1.point.equals(finish) && !vert1.point.equals(start))
                    usedPoints.add(vert1.point);
            }
        }

        LinkedList<Point> shortestPath = new LinkedList<Point>();
        if (finishVert != null) {
            for (Vertex vertex = finishVert.predecessor; vertex.predecessor != null; vertex = vertex.predecessor) {
                shortestPath.add(vertex.point);
            }
        }

        return shortestPath;
    }

    protected int findHeuristic(Point p1, Point p2) {
        int x = p1.getX()-p2.getX();
        int y = p1.getY()-p2.getY();
        return Math.abs(x)+Math.abs(y);
    }

    private class Vertex {
        Point point;
        int fDistance = Integer.MAX_VALUE;
        int gDistance = Integer.MAX_VALUE;
        Vertex predecessor;

        Vertex(Point point) {
            this.point = point;
        }

        Vertex(Point point,int gDistance,Vertex predecessor) {
            this.point = point;
            this.predecessor = predecessor;
            this.gDistance = gDistance;
            this.fDistance = gDistance+findHeuristic(point,finish);
        }

        @Override
        public boolean equals(Object other){
            return this.point.equals(((Vertex)other).point);
        }
    }
}


