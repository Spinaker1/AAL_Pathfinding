package sample.model.algorithms;

import sample.Color;
import sample.Point;
import sample.model.Model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar implements Algorithm {
    private Model model;
    private Point finish;

    public AStar(Model model) {
        this.model = model;
    }

    @Override
    public void findPath(Point start, Point finish) {
        this.finish = finish;
        Vertex finishVert;

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

                if (vert.point.getX() < 0 || vert.point.getX() >= model.getGrid().getWidth())
                    continue;
                if (vert.point.getY() < 0 || vert.point.getY() >= model.getGrid().getHeight())
                    continue;
                if (model.getGrid().tiles[vert.point.getX()][vert.point.getY()].getColor() == Color.BLACK)
                    continue;

                for (Iterator<Vertex> iter1 = closeSet.iterator(); iter1.hasNext(); )
                {
                    Vertex vert1 = (Vertex) iter1.next();
                    if (vert1.point.equals(vert.point)) {
                        continue main_loop;
                    }
                }

                for (Iterator<Vertex> iter1 = openSet.iterator(); iter1.hasNext(); )
                {
                    Vertex vert1 = (Vertex) iter1.next();
                    if (vert1.point.equals(vert.point))
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

        for (Iterator<Vertex> iter = closeSet.iterator(); iter.hasNext(); ) {
            Vertex vert1 = iter.next();
            if (!vert1.point.equals(finish) && !vert1.point.equals(start))
                model.getController().setBlue(vert1.point);
        }

        for (Vertex vertex = finishVert.predecessor; vertex.predecessor != null; vertex=vertex.predecessor) {
            model.getController().setYellow(vertex.point);
        }
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
    }
}


