package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.model.Model;
import sample.view.View;
import sample.controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        View view = new View(primaryStage);
        Model model = new Model();
        Controller controller =  new Controller(view,model);
        view.setController(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
