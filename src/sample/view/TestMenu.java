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

public class TestMenu extends VBox {
    private View view;
    private HBox[] hBoxes;

    public TestMenu(View view) {
        super(20);
        this.view = view;
        setPadding(new Insets(5));
        setAlignment(Pos.CENTER);
        setupInterface();
    }

    private void setupInterface() {
        hBoxes = new HBox[5];

        for (int i = 0; i < hBoxes.length; i++) {
            hBoxes[i] = new HBox(5);
        }
        this.getChildren().addAll(hBoxes);

        Text text = new Text("Ilość wygenerowanych rastrów:");
        TextField textField = new TextField();
        textField.setMaxWidth(50);
        hBoxes[0].getChildren().addAll(text, textField);

        Text text1 = new Text("Ilość testów na raster:");
        TextField textField1 = new TextField();
        textField1.setMaxWidth(50);
        hBoxes[1].getChildren().addAll(text1, textField1);

        Text text2 = new Text("Rozmiar:");
        TextField widthTextField = new TextField();
        TextField heightTextField = new TextField();
        widthTextField.setMaxWidth(50);
        heightTextField.setMaxWidth(50);
        hBoxes[2].getChildren().addAll(text2, widthTextField, heightTextField);

        Text text3 = new Text("Zapisz logi do pliku:");
        TextField textField2 = new TextField();
        textField2.setMaxWidth(100);
        hBoxes[3].getChildren().addAll(text3,textField2);

        Button testButton = new Button("Rozpocznij testy");
        hBoxes[4].getChildren().addAll(testButton);

        testButton.setOnAction(e -> {
            int generatedGrids = Integer.parseInt(textField.getText());
            int testsPerGrid = Integer.parseInt(textField1.getText());
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            String filename = textField2.getText();

            view.getController().conductTests(filename,generatedGrids,testsPerGrid,width,height);
        });
    }
}
