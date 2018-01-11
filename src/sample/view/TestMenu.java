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
        hBoxes = new HBox[3];

        for (int i = 0; i < hBoxes.length; i++) {
            hBoxes[i] = new HBox(5);
        }
        this.getChildren().addAll(hBoxes);

        Text text2 = new Text("Rozmiar:");
        TextField widthTextField = new TextField();
        TextField heightTextField = new TextField();
        widthTextField.setMaxWidth(50);
        heightTextField.setMaxWidth(50);
        hBoxes[0].getChildren().addAll(text2, widthTextField, heightTextField);

        Text text3 = new Text("Zapisz logi do pliku:");
        TextField textField2 = new TextField();
        textField2.setMaxWidth(100);
        hBoxes[1].getChildren().addAll(text3,textField2);

        Button testButton = new Button("Rozpocznij testy");
        hBoxes[2].getChildren().addAll(testButton);

        testButton.setOnAction(e -> {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            String filename = textField2.getText();

            view.getController().conductTests(filename,width,height);
        });
    }
}
