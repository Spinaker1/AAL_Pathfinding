package sample.model;

import javafx.application.Platform;
import sample.Color;
import sample.Point;
import sample.controller.Controller;
import sample.model.algorithms.AStar;
import sample.model.algorithms.Algorithm;
import sample.model.algorithms.Dijkstra;
import sample.model.algorithms.SampleAlgorithm;

public class Model {
    private Grid grid;
    private Controller controller;

    private Point start;
    private Point finish;

    public Model() {
    }

    public void findPath(int chosenAlgorithm) {
        Algorithm algorithm;
        switch (chosenAlgorithm) {
            case 0: algorithm = new AStar(this);
                    break;
            case 1: algorithm = new Dijkstra(this);
                    break;
            case 2: algorithm = new SampleAlgorithm(this);
                    break;
            default:
                    algorithm = new AStar(this);
        }
        algorithm.findPath(start,finish);
    }

    public void createBlankGrid(int width, int height) {
        grid = new Grid(width,height);
    }

    public void generateGrid(int width, int height) {
        GridGenerator gridGenerator = new GridGenerator(width,height);
        grid = gridGenerator.generateGrid();
        for (int i=0; i<height ; i++) {
            for (int j=0; j<width; j++) {
                if (grid.tiles[j][i].getColor() == Color.WHITE) {
                    final Point p = new Point(j, i);
                    Platform.runLater(() -> controller.setWhite(p));
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
