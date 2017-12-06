package sample.model;

import sample.*;

public class Grid implements Cloneable {
    private int width;
    private int height;
    public Tile tiles[][];

    public Grid(int width, int height) {
        this.width=width;
        this.height=height;

        tiles = new Tile[width][height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                tiles[i][j] = new Tile(new Point(i, j));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Grid grid = new Grid(width,height);
        for (int i=0;i<height;i++) {
            for (int j=0;j<width;j++) {
                Color color = this.tiles[j][i].getColor();
                grid.tiles[j][i].setColor(color);
            }
        }
        return grid;
    }
}
