package sample.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Point;

public class Board extends BorderPane {
    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private HBox boardHBox;
    private int sceneSize;

    public Board(int width, int height, int sceneSize) {
        tiles = new Tile[width][height];
        this.height = height;
        this.width = width;
        this.sceneSize = sceneSize;

        HBox hBox = setupEmptyTiles();
        setScale(hBox);
    }

    private HBox setupEmptyTiles() {
        boardHBox = new HBox();

        for (int i = 0; i < width; i++) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile(new Point(i,j));
                vBox.getChildren().add(tiles[i][j]);
            }
            boardHBox.getChildren().add(vBox);
        }

        boardHBox.setMinSize(1, 1);
        setCenter(boardHBox);
        boardHBox.setAlignment(Pos.CENTER);

        return boardHBox;
    }

    private void setScale(HBox hBox) {
        double x = height > width ? height : width;
        x = x * tiles[0][0].getMaxHeight();
        x = (0.9*sceneSize)/x;
        hBox.setScaleX(x);
        hBox.setScaleY(x);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    public void disable() {
        Platform.runLater(() -> boardHBox.setMouseTransparent(true));
    }

    public void enable() {
        Platform.runLater(() -> boardHBox.setMouseTransparent(false));
    }

    public void setWhite(Point point) {
        tiles[point.getX()][point.getY()].setWhiteImage();
    }
}


