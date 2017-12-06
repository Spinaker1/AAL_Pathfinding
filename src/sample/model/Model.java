package sample.model;

import javafx.application.Platform;
import sample.Color;
import sample.Point;
import sample.controller.Controller;
import sample.model.algorithms.Algorithm;

public class Model {
    private Grid grid;
    private Controller controller;
    private Algorithm algorithm;

    public Model() {

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
}
