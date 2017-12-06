package sample.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PawnSprite extends ImageView {
    private static ImageManager imageManager = new ImageManager();

    PawnSprite() {
        setBlack();
    }

    public void setBlack() {
        setImage(imageManager.blackImage);
    }

    public void setWhite() {
        setImage(imageManager.whiteImage);
    }

    public void setYellow() { setImage(imageManager.yellowImage); }

    public void setGreen() { setImage(imageManager.greenImage); }

    public void setRed() { setImage(imageManager.redImage); }
}

class ImageManager {
    public Image blackImage;
    public Image whiteImage;
    public Image yellowImage;
    public Image greenImage;
    public Image redImage;

    ImageManager() {
        whiteImage = new Image("file:src/images/white.png");
        blackImage = new Image("file:src/images/black.png");
        yellowImage = new Image("file:src/images/yellow.png");
        greenImage = new Image("file:src/images/green.png");
        redImage = new Image("file:src/images/red.png");
    }
}