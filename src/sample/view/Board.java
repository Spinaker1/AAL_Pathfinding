package sample.view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.Point;

public class Board extends HBox {
    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private double sceneHeight;
    private short clicksCount = 0;
    private View view;

    public Board(int width, int height, double sceneHeight, View view) {
        tiles = new Tile[width][height];
        this.height = height;
        this.width = width;
        this.sceneHeight = sceneHeight;
        this.view = view;

        setMinSize(1,1);

        setupEmptyTiles();
        setScale();
    }

    private void setupEmptyTiles() {
        for (int i = 0; i < width; i++) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(new Point(i,j), this);
                vBox.getChildren().add(tiles[i][j]);
            }
           getChildren().add(vBox);
        }
    }

    private void setScale() {
        double x = height > width ? height : width;
        x = x * tiles[0][0].getMaxHeight();
        x = (0.9* sceneHeight)/x;
        setScaleX(x);
        setScaleY(x);
    }

    public void setWhite(Point point) {
        tiles[point.getX()][point.getY()].setWhiteImage();
    }

    public void setYellow(Point point) {
        tiles[point.getX()][point.getY()].setYellowImage();
    }

    public void setBlue(Point point) {
        tiles[point.getX()][point.getY()].setBlueImage();
    }

    public short getClicksCount() {
        return clicksCount;
    }

    public void setClicksCount(short clicksCount) {
        this.clicksCount = clicksCount;
    }

    public View getView() { return view; }

    public void clear() {
        getChildren().clear();
        setupEmptyTiles();
        setScale();
    }
}


