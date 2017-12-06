package sample.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Point;
import sample.controller.Controller;

public class View {
    private Controller controller;

    private final int sceneWidth = 1000;
    private final int sceneHeight = 700;
    private Scene scene;
    private Board board;
    private MainMenu mainMenu;
    private BorderPane pane;

    public View(Stage primaryStage) {
        pane = new BorderPane();
        mainMenu = new MainMenu(this);
        pane.setRight(mainMenu);

        scene = new Scene(pane);

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createBoard(int width, int height) {
        board = new Board(width, height, scene.getHeight());
        board.setAlignment(Pos.CENTER);
        pane.setCenter(board);
    }

    public void setWhite(Point point) { board.setWhite(point);}

    public void setYellow(Point point) { board.setYellow(point);}

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
