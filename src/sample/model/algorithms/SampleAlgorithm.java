package sample.model.algorithms;

import sample.Color;
import sample.Point;
import sample.model.Model;

import java.util.Iterator;
import java.util.LinkedList;

public class SampleAlgorithm implements Algorithm {
    private LinkedList<Vertex> vertList = new LinkedList<Vertex>();
    private Model model;

    public SampleAlgorithm(Model model) {
        this.model = model;
    }

    public void findPath(Point start, Point finish) {
        vertList.push(new Vertex(finish, 0,null));

        Vertex startVert;
        LinkedList<Vertex> vertsToAdd = new LinkedList<Vertex>();

        main_loop:
        for (;;) {
            for (Iterator<Vertex> iter = vertList.iterator(); iter.hasNext(); ) {
                Vertex vert = (Vertex) iter.next();
                vertsToAdd.push(new Vertex(new Point(vert.point.getX(), vert.point.getY() + 1), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX(), vert.point.getY() - 1), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX() + 1, vert.point.getY()), vert.distance + 1, vert));
                vertsToAdd.push(new Vertex(new Point(vert.point.getX() - 1, vert.point.getY()), vert.distance + 1, vert));
            }

            small_loop:
            for (Iterator<Vertex> iter = vertsToAdd.iterator(); iter.hasNext(); ) {
                Vertex vert1 = (Vertex) iter.next();
                if (vert1.point.getX() < 0 || vert1.point.getX() >= model.getGrid().getWidth())
                    continue;
                if (vert1.point.getY() < 0 || vert1.point.getY() >= model.getGrid().getHeight())
                    continue;
                if (model.getGrid().tiles[vert1.point.getX()][vert1.point.getY()].getColor() == Color.BLACK)
                    continue;
                for (Iterator<Vertex> iter1 = vertList.iterator(); iter1.hasNext(); )
                {
                    Vertex vert2 = (Vertex) iter1.next();
                    if (vert1.point.equals(vert2.point) && vert1.distance >= vert2.distance)
                        continue small_loop;
                }
                vertList.push(vert1);

                if (vert1.point.equals(start))
                {
                    startVert = vert1;
                    break main_loop;
                }
            }

            vertsToAdd.clear();
        }

        for (Iterator<Vertex> iter = vertList.iterator(); iter.hasNext(); ) {
            Vertex vert1 = iter.next();
            if (!vert1.point.equals(finish) && !vert1.point.equals(start))
                model.getController().setBlue(vert1.point);
        }

        for (Vertex vertex = startVert.predecessor; vertex.predecessor != null; vertex=vertex.predecessor) {
            model.getController().setYellow(vertex.point);
        }
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
    }
}
