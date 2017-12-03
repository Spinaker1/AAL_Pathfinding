package sample.model;

import sample.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Grid implements Cloneable {
    private int width;
    private int height;
    Tile tiles[][];

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

    public void show() {
        for (int i=0;i<height;i++) {
            for (int j=0;j<width;j++) {
                if (tiles[j][i].getColor() ==  Color.BLACK)
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void writeFile() {
        try {
            PrintWriter printWriter = new PrintWriter("plik.txt");
            printWriter.println(width);
            printWriter.println(height);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (tiles[j][i].getColor() == Color.BLACK)
                        printWriter.print("0");
                    else
                        printWriter.print("1");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (FileNotFoundException e) {};
    }
}
