package sample.model;

import sample.Color;
import sample.Point;

public class Tile {
    private Point location;
    private Color color;

    Tile (Point location) {
        this.color = Color.BLACK;
        this.location = location;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
