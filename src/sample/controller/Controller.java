package sample.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import sample.Color;
import sample.Point;
import sample.model.Grid;
import sample.model.Model;
import sample.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void generateGrid(int width, int height) {
        view.createBoard(width, height);
        model.generateGrid(width, height);
        fillWhiteTiles();
    }

    public void findPath(int chosenAlgorithm) {
        fillWhiteTiles();
        LinkedList<Point> usedPoints = new LinkedList<Point>();
        LinkedList<Point> shortestPath = model.findPath(chosenAlgorithm, usedPoints);
        for (Point point : usedPoints) {
            view.getBoard().setBlue(point);
        }
        for (Point point : shortestPath) {
            view.getBoard().setYellow(point);
        }
        view.getMainMenu().setMinPathLength(shortestPath.size());
    }

    public void setStart(Point start) {
        model.setStart(start);
    }

    public void setFinish(Point finish) {
        model.setFinish(finish);
    }

    public void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int w = Integer.parseInt(scanner.nextLine());
            int h = Integer.parseInt(scanner.nextLine());
            view.createBoard(w, h);
            model.createBlankGrid(w, h);

            String text = "";
            int i = 0;

            while (scanner.hasNextLine()) {
                text = scanner.nextLine();
                for (int j = 0; j < text.length(); j++) {
                    int x = Integer.parseInt(text.charAt(j) + "");
                    Point point = new Point(j, i);
                    model.setColor(point, x);
                    if (x == 1)
                        view.getBoard().setWhite(point);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.WARNING, "Nie można otworzyć pliku " + fileName + "!", ButtonType.OK);
            alert.showAndWait();
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(AlertType.WARNING, "Plik " + fileName + " jest pusty!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void writeFile(String filename) {
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            Grid grid = model.getGrid();
            printWriter.println(grid.getWidth());
            printWriter.println(grid.getHeight());
            for (int i = 0; i < grid.getHeight(); i++) {
                for (int j = 0; j < grid.getWidth(); j++) {
                    if (grid.tiles[j][i].getColor() == Color.BLACK)
                        printWriter.print("0");
                    else
                        printWriter.print("1");
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
        }
        ;
    }

    public void conductTests(String filename, int width, int height) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename, true));
            LinkedList<Point> usedTiles = new LinkedList<Point>();

            model.createBlankGrid(width, height);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++)
                    model.setColor(new Point(i, j), 1);
            }

            printWriter.print(width + ";" + height + ";");

            model.setStart(new Point(0, 0));
            model.setFinish(new Point(width - 1, height - 1));

            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 10; l++) {
                    usedTiles.clear();
                    model.findPath(k, usedTiles);
                }
                long start = System.nanoTime();
                model.findPath(k, usedTiles);
                long stop = System.nanoTime();
                printWriter.print(usedTiles.size() + ";" + ((double)(stop-start) / 1000000) + ";");
            }

            printWriter.println();

            printWriter.close();
        } catch (FileNotFoundException e) {
        }

        Alert alert = new Alert(AlertType.INFORMATION, "Koniec testów", ButtonType.OK);
        alert.showAndWait();
    }

    private void fillWhiteTiles() {
        Point start = model.getStart();
        Point finish = model.getFinish();
        LinkedList<Point> whiteTiles = model.getWhiteTiles();

        for (Point tile : whiteTiles) {
            if ((start == null || finish == null) || (!tile.equals(start) && !tile.equals(finish))) {
                view.getBoard().setWhite(tile);
            }
        }
    }
}
