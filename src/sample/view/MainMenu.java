package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenu extends VBox {
    private View view;
    private HBox[] hBoxes;

    public MainMenu(View view) {
        super(20);
        this.view = view;
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupButtons();
    }

    private void setupButtons() {
        hBoxes = new HBox[3];

        for (int i=0; i<hBoxes.length; i++) {
            hBoxes[i] = new HBox(5);
        }
        this.getChildren().addAll(hBoxes);

        Text text = new Text("Wczytaj raster z pliku:");
        Button readButton = new Button("Wczytaj");
        TextField textField = new TextField();
        textField.setMaxWidth(100);
        hBoxes[0].getChildren().addAll(text,textField,readButton);

        Text text2 = new Text("Wygeneruj raster:");
        Button generateButton = new Button("Stwórz");
        TextField widthTextField = new TextField();
        TextField heightTextField = new TextField();
        widthTextField.setMaxWidth(50);
        heightTextField.setMaxWidth(50);
        hBoxes[1].getChildren().addAll(text2,widthTextField, heightTextField, generateButton);

        Text text3 = new Text("Zapisz raster do pliku:");
        Button writeButton = new Button("Zapisz");
        TextField textField2 = new TextField();
        textField2.setMaxWidth(100);
        hBoxes[2].getChildren().addAll(text3,textField2,writeButton);

        createToggleGroup();

        readButton.setOnAction(e -> {
            String fileName = textField.getText();
            view.getController().readFile(fileName);
        });

        generateButton.setOnAction(e -> {
            if (widthTextField.getText().length() > 0 && heightTextField.getText().length() > 0) {
                int width = Integer.parseInt(widthTextField.getText());
                int height = Integer.parseInt(heightTextField.getText());
                view.getController().generateGrid(width, height);
            }
        });
    }

    private void createToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        RadioButton radioButtons[] = new RadioButton[4];
        String names[] = {"A*", "Dijkstra", "Bellman-Ford", "Brutalny"};

        for (int i=0; i<radioButtons.length; i++) {
            radioButtons[i] = new RadioButton(names[i]);
            radioButtons[i].setToggleGroup(group);
        }

        radioButtons[0].setSelected(true);

        getChildren().addAll(radioButtons);
    }
}