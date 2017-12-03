package sample.model;

import sample.*;

import java.util.Random;

public class GridGenerator {
    int width, height;
    Random rand = new Random();
    private int fillprob = 40;
    int r1_cutoff = 5, r2_cutoff = 2;
    private Grid grid, grid2;

    public GridGenerator(int width, int height) {
        this.width=width;
        this.height=height;
    }

    public Grid generateGrid() {
        grid = new Grid(width, height);
        grid2 = new Grid(width, height);
        initGrid();
        for (int i=1;i<=12;i++)
            generate();
        return grid;
    }

    private Color randomColor() {
        if (rand.nextInt(100) < fillprob)
            return Color.BLACK;
        else
            return Color.WHITE;
    }

    private void initGrid() {
        for (int i=0; i<height ; i++) {
            for (int j=0; j<width; j++) {
                grid.tiles[j][i].setColor(randomColor());
            }
        }

        for (int i=0;i<height;i++) {
            grid.tiles[0][i].setColor(Color.BLACK);
            grid.tiles[width-1][i].setColor(Color.BLACK);
        }

        for (int i=0;i<width;i++) {
            grid.tiles[i][0].setColor(Color.BLACK);
            grid.tiles[i][height-1].setColor(Color.BLACK);
        }
    }

    private void generate() {
        int height = grid.getHeight();
        int width = grid.getWidth();

        for (int y=1; y<height-1 ; y++) {
            for (int x=1; x<width-1; x++) {
                int r1Count = 0;
                int r2Count = 0;

                for (int i=y-1; i<=y+1; i++) {
                    for (int j=x-1; j<=x+1; j++) {

                        if (grid.tiles[j][i].getColor() == Color.BLACK)
                            r1Count++;
                    }
                }

                for (int i=y-2; i<=y+2; i++) {
                    for (int j =x-2; j <= x+2; j++) {
                        if(Math.abs(i-y)==2 && Math.abs(j-x)==2)
                            continue;
                        if(i<0 || j<0 || i>=height || j>=width)
                            continue;
                        if(grid.tiles[j][i].getColor() == Color.BLACK)
                            r2Count++;
                    }
                }

                if (r1Count >= 5 || r2Count <= 2)
                    grid2.tiles[x][y].setColor(Color.BLACK);
                else
                    grid2.tiles[x][y].setColor(Color.WHITE);
            }
        }


        try {
            grid = (Grid) grid2.clone();
        } catch(CloneNotSupportedException e) {};
    }
}
