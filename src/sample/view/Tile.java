package sample.view;

import javafx.scene.control.Button;
import sample.Point;

public class Tile extends Button {
    private final PawnSprite pawnSprite;
    private Point location;
    private Board board;

    public Tile(Point location, Board board) {
        this.location = location;
        this.board = board;
        pawnSprite = new PawnSprite();

        setMinSize(50, 50);
        setMaxSize(50, 50);
        setGraphic(pawnSprite);
        setStyle("-fx-focus-color: transparent;");
        setMouseTransparent(true);

        onClick();
    }

    public void setYellowImage() {
        pawnSprite.setYellow();
    }

    public void setBlueImage() {
        pawnSprite.setBlue();
    }

    public void setWhiteImage() {
        setMouseTransparent(false);
        pawnSprite.setWhite();
    }

    private void onClick() {
        setOnAction(e -> {
            setMouseTransparent(true);

            short clicksCount = (short) (board.getClicksCount()+1);
            switch(clicksCount) {
                case 1: pawnSprite.setGreen();
                        board.getView().getController().setStart(location);
                        break;
                case 2: pawnSprite.setRed();
                        board.getView().getController().setFinish(location);
                        board.setMouseTransparent(true);
                        clicksCount=0;
                        break;
            };
            board.setClicksCount(clicksCount);
        });
    }
}
