package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenu extends VBox {
    private View view;

    public MainMenu(View view) {
        super(20);
        this.view = view;
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupButtons();
    }

    private void setupButtons() {
        HBox[] hBoxes = new HBox[2];

        hBoxes[0] = new HBox();
        hBoxes[0].setAlignment(Pos.CENTER);
        hBoxes[1] = new HBox();
        hBoxes[1].setAlignment(Pos.CENTER);
        this.getChildren().addAll(hBoxes);

        Text text = new Text("Wczytaj raster z pliku:");
        Button readButton = new Button("Wczytaj");
        TextField textField = new TextField();
        hBoxes[0].getChildren().addAll(text,textField,readButton);

        Button generateButton = new Button("Stwórz");
        TextField widthTextField = new TextField();
        TextField heightTextField = new TextField();
        hBoxes[1].getChildren().addAll(widthTextField, heightTextField, generateButton);

        readButton.setOnAction(e -> {
            String fileName = textField.getText();
            view.getController().readFile(fileName);
        });

        generateButton.setOnAction(e -> {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            view.getController().generateGrid(width,height);
        });
    }
}