package sample.model.algorithms;

import sample.Point;
import sample.model.Grid;

public class AStar {
    private Grid grid;

    AStar(Grid grid) {
        this.grid = grid;
    }

    private int findHeuristic(Point p1, Point p2) {
        int x = p1.getX()-p2.getX();
        int y = p1.getY()-p2.getY();
        return Math.abs(x)+Math.abs(y);
    }

    public void createHeuristicTable() {
        int height = grid.getHeight();
        int width = grid.getWidth();

        Point p1 = new Point(2,1);
        for (int i=0; i<height ; i++) {
            for (int j=0; j<width; j++) {
                Point p2 = grid.tiles[j][i].getLocation();
                System.out.print(findHeuristic(p1,p2));
            }
            System.out.println();
        }
    }
}
