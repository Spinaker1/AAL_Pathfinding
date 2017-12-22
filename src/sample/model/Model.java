package sample.model;

import sample.Color;
import sample.Point;
import sample.controller.Controller;
import sample.model.algorithms.AStar;
import sample.model.algorithms.Algorithm;
import sample.model.algorithms.Dijkstra;
import sample.model.algorithms.SampleAlgorithm;

import java.util.LinkedList;

public class Model {
    private Grid grid;
    private Controller controller;

    private Point start = null;
    private Point finish = null;

    public Model() {
    }

    public void findPath(int chosenAlgorithm) {
        fillWhiteTiles();
        Algorithm algorithm;
        switch (chosenAlgorithm) {
            case 0: algorithm = new AStar();
                    break;
            case 1: algorithm = new Dijkstra();
                    break;
            case 2: algorithm = new SampleAlgorithm();
                    break;
            default:
                    algorithm = new AStar();
        }

        LinkedList<Point> usedPoints = new LinkedList<Point>();
        LinkedList<Point> shortestPath = algorithm.findPath(grid, start, finish, usedPoints, false);
        for (Point point: usedPoints) {
            controller.setBlue(point);
        }
        for (Point point: shortestPath) {
            controller.setYellow(point);
        }
    }

    public void createBlankGrid(int width, int height) {
        grid = new Grid(width,height);
    }

    public void generateGrid(int width, int height) {
        GridGenerator gridGenerator = new GridGenerator(width,height);
        grid = gridGenerator.generateGrid();
        fillWhiteTiles();
    }

    private void fillWhiteTiles () {
        for (int i=0; i<grid.getHeight() ; i++) {
            for (int j=0; j<grid.getWidth(); j++) {
                if (grid.tiles[j][i].getColor() == Color.WHITE) {
                    final Point p = new Point(j, i);
                    if ((start == null || finish == null) || (!p.equals(start) && !p.equals(finish)))
                        controller.setWhite(p);
                }
            }
        }
    }

    public void setColor(Point point, int color)
    {
        Color c;
        if (color == 1)
            c=Color.WHITE;
        else
            c=Color.BLACK;
        grid.tiles[point.getX()][point.getY()].setColor(c);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setStart(Point start) { this.start = start; }

    public void setFinish(Point finish) { this.finish = finish; }

    public Grid getGrid() {
        return grid;
    }
}
