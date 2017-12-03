package sample.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import sample.Point;
import sample.model.Model;
import sample.view.View;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void generateGrid(int width, int height) {
        view.createBoard(width,height);
        model.generateGrid(width,height);
    }

    public void setWhite(Point p) {
        view.setWhite(p);
    }

    public void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int w = Integer.parseInt(scanner.nextLine());
            int h = Integer.parseInt(scanner.nextLine());
            view.createBoard(w,h);
            model.createBlankGrid(w,h);

            String text = "";
            int i=0;

            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                for (int j=0; j<text.length(); j++)
                {
                    int x = Integer.parseInt(text.charAt(j)+"");
                    Point point = new Point(j,i);
                    model.setColor(point,x);
                    if (x==1)
                        view.setWhite(point);
                }
                i++;
            }
        }
        catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.WARNING, "Nie można otworzyć pliku "+fileName+"!", ButtonType.OK);
            alert.showAndWait();
        }
        catch (NoSuchElementException e) {
            Alert alert = new Alert(AlertType.WARNING, "Plik "+fileName+" jest pusty!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}