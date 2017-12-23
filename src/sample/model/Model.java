package sample.model;

import sample.Color;
import sample.Point;
import sample.controller.Controller;
import sample.model.algorithms.AStar;
import sample.model.algorithms.Algorithm;
import sample.model.algorithms.Dijkstra;
import sample.model.algorithms.BreadthFirstSearch;

import java.util.LinkedList;

public class Model {
    private Grid grid;

    private Point start = null;
    private Point finish = null;

    public LinkedList<Point> findPath(int chosenAlgorithm, LinkedList<Point> usedPoints) {
        Algorithm algorithm;
        switch (chosenAlgorithm) {
            case 0: algorithm = new AStar();
                    break;
            case 1: algorithm = new Dijkstra();
                    break;
            case 2: algorithm = new BreadthFirstSearch();
                    break;
            default:
                    algorithm = new AStar();
        }

        return algorithm.findPath(grid, start, finish, usedPoints, false);
    }

    public LinkedList<Point> getWhiteTiles() {
        LinkedList<Point> whiteTiles = new LinkedList<Point>();
        for (int i=0; i<grid.getHeight() ; i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.tiles[j][i].getColor() == Color.WHITE) {
                    whiteTiles.push(new Point(j, i));
                }
            }
        }

        return whiteTiles;
    }

    public void createBlankGrid(int width, int height) {
        grid = new Grid(width,height);
    }

    public void generateGrid(int width, int height) {
        GridGenerator gridGenerator = new GridGenerator(width,height);
        grid = gridGenerator.generateGrid();
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

    public void setStart(Point start) { this.start = start; }

    public void setFinish(Point finish) { this.finish = finish; }

    public Point getStart() { return start; }

    public Point getFinish() { return finish; }

    public Grid getGrid() {
        return grid;
    }
}
