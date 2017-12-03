package sample.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Point;
import sample.controller.Controller;

public class View {
    private Controller controller;
    private Scene scene;
    private Board board;
    private MainMenu mainMenu;

    public View(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        mainMenu = new MainMenu(this);
        scene = new Scene(mainMenu, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createBoard(int width, int height) {
        board = new Board(width, height);
        scene.setRoot(board);
    }

    public void setWhite(Point point) { board.setWhite(point);}

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
