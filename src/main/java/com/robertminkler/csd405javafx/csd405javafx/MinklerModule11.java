package com.robertminkler.csd405javafx.csd405javafx;

/*
 * Robert Minkler
 * CSD 405 Module 11
 * Dec 12, 2024
 *
 * Write a program that displays various figures.
 * Include radio buttons selections for changing the display figure to the one selected.
 * Include a checkbox for filling and clearing the displayed figure with a random color.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class MinklerModule11 extends Application {

    // set padding size
    private static final int GAP = 30;
    private static final String RANDOM_CHK_LABEL_1 = "Fill With a Random Color";
    private static final String RANDOM_CHK_LABEL_2 = "Don't even think about it.";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Window Layout using BorderPane
        BorderPane borderPane = new BorderPane();
        // set BG color
        borderPane.setBackground(new Background(
                new BackgroundFill(
                        Color.BLACK,
                        null,
                        null
                )
        ));

        // Create StackPane and place it in the window
        ShapePane shapePane = new ShapePane();
        borderPane.setCenter(shapePane);

        // a VBox for shape radio buttons
        VBox shapeButtons = new VBox();
        shapeButtons.setSpacing(GAP);
        shapeButtons.setPadding(new Insets(GAP));
        shapeButtons.setAlignment(Pos.CENTER_LEFT);
        borderPane.setLeft(shapeButtons);

        // a VBox for Checkbox
        VBox colorCheckBox = new VBox();
        colorCheckBox.setSpacing(GAP);
        colorCheckBox.setPadding(new Insets(GAP));
        colorCheckBox.setAlignment(Pos.CENTER);
        borderPane.setRight(colorCheckBox);

        // Create Checkbox to select random
        CheckBox chkRandomColor = new CheckBox(RANDOM_CHK_LABEL_1);
        colorCheckBox.getChildren().add(chkRandomColor);

        // share chkRandomColor with innerClass
        shapePane.setChkRandomColor(chkRandomColor);

        // Refresh when checkbox is checked
        chkRandomColor.setOnAction(_ -> shapePane.drawShape());

        // Create Radio Buttons
        RadioButton rBtnArc = new RadioButton("Arc");
        RadioButton rBtnCircle = new RadioButton("Circle");
        RadioButton rBtnEllipse = new RadioButton("Ellipse");
        RadioButton rBtnSquare = new RadioButton("Square");
        RadioButton rBtnRectangle = new RadioButton("Rectangle");
        RadioButton rBtnPacMan = new RadioButton("PacMan");

        // set label color for all labels
        rBtnArc.setTextFill(Color.LIGHTGRAY);
        rBtnCircle.setTextFill(Color.LIGHTGRAY);
        rBtnSquare.setTextFill(Color.LIGHTGRAY);
        rBtnRectangle.setTextFill(Color.LIGHTGRAY);
        rBtnEllipse.setTextFill(Color.LIGHTGRAY);
        rBtnPacMan.setTextFill(Color.LIGHTGRAY);
        chkRandomColor.setTextFill(Color.LIGHTGRAY);

        // Set toggle group for the shape radio buttons
        ToggleGroup shapeToggle = new ToggleGroup();
        rBtnArc.setToggleGroup(shapeToggle);
        rBtnCircle.setToggleGroup(shapeToggle);
        rBtnEllipse.setToggleGroup(shapeToggle);
        rBtnSquare.setToggleGroup(shapeToggle);
        rBtnRectangle.setToggleGroup(shapeToggle);
        rBtnPacMan.setToggleGroup(shapeToggle);

        // Set Radio Button actions
        rBtnArc.setOnAction(_ -> shapePane.drawShape(ShapePane.ARC));
        rBtnCircle.setOnAction(_ -> shapePane.drawShape(ShapePane.CIRCLE));
        rBtnEllipse.setOnAction(_ -> shapePane.drawShape(ShapePane.ELLIPSE));
        rBtnSquare.setOnAction(_ -> shapePane.drawShape(ShapePane.SQUARE));
        rBtnRectangle.setOnAction(_ -> shapePane.drawShape(ShapePane.RECTANGLE));
        rBtnPacMan.setOnAction(_ -> shapePane.drawShape(ShapePane.PAC_MAN));

        // Place radio buttons in vbox
        shapeButtons.getChildren().addAll(rBtnArc, rBtnCircle, rBtnEllipse, rBtnRectangle, rBtnSquare, rBtnPacMan);

        // Set the scene, stage, and show it.
        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setTitle("Robert Minkler Module 11 Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // An inner class to control the shape in the central pane
    public static class ShapePane extends StackPane {

        // final static variables to determine what shape to display
        public static final int ARC = 0;
        public static final int CIRCLE = 1;
        public static final int SQUARE = 2;
        public static final int RECTANGLE = 3;
        public static final int ELLIPSE = 4;
        public static final int PAC_MAN = 5;

        // stores reference to the checkbox of outer class
        private CheckBox chkRandomColor;

        // Store the current shape as an int. -1 selects no shape
        private int currShape = -1;

        private void setChkRandomColor(CheckBox checkBox) {
            // setter - shares checkbox reference from inner class
            chkRandomColor = checkBox;
        }

        private void setCurrShape(int shape) {
            // Setter to store the currently selected shape
            currShape = shape;
        }

        private Color getColor() {
            // returns default LIGHT GRAY or randomColor based on checkbox status
            if (chkRandomColor.isSelected()) {
                return Color.rgb(
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256)
                );
            } else {
                return Color.LIGHTGRAY;
            }
        }

        public void drawShape() {
            // Redraw the current shape - used when random color selection changes.
            drawShape(currShape);
        }

        public void drawShape(int shape) {
            // Set the shape pane with the selected shape

            // clear the existing shapes from pane
            getChildren().clear();

            // set the object shape string to the current shape to recall later
            setCurrShape(shape);

            // enable the random color checkbox and update label
            // It may have been altered by PacMan
            chkRandomColor.setDisable(false);
            chkRandomColor.setText(RANDOM_CHK_LABEL_1);

            // add the currently selected shape to the pane
            switch (shape) {
                case ShapePane.ARC:
                    getChildren().add(getArc());
                    break;
                case ShapePane.CIRCLE:
                    getChildren().add(getCircle());
                    break;
                case ShapePane.SQUARE:
                    getChildren().add(getSquare());
                    break;
                case ShapePane.RECTANGLE:
                    getChildren().add(getRectangle());
                    break;
                case ShapePane.ELLIPSE:
                    getChildren().add(getEllipse());
                    break;
                case ShapePane.PAC_MAN:
                    getChildren().add(getPacMan());
            }
        }

        private Arc getArc() {
            // Returns an arc shape
            Arc arc = new Arc();
            arc.setFill(getColor());
            arc.setRadiusX(150);
            arc.setRadiusY(100);
            arc.setStartAngle(0);
            arc.setLength(100);
            arc.setType(ArcType.ROUND);
            return arc;
        }

        private Rectangle getRectangle() {
            // Returns rectangle shape
            return new Rectangle(300, 200, getColor());
        }

        private Rectangle getSquare() {
            // Returns square shape
            return new Rectangle(200, 200, getColor());
        }

        private Ellipse getEllipse() {
            // Returns ellipse shape
            Ellipse ellipse = new Ellipse(150, 100);
            ellipse.setStrokeWidth(2);
            ellipse.setFill(getColor());
            return ellipse;
        }

        private Circle getCircle() {
            // Returns circle shape
            return new Circle(100, getColor());
        }

        private Arc getPacMan() {
            // Returns an arc shaped as PacMan
            Arc arc = new Arc();
            arc.setFill(Color.rgb(252, 234, 63)); // PacMan STAYS yellow!!!
            arc.setRadiusX(150);
            arc.setRadiusY(150);
            arc.setStartAngle(45);
            arc.setLength(270);
            arc.setType(ArcType.ROUND);

            // disable random color checkbox when pacman is visible and update label
            chkRandomColor.setDisable(true);
            chkRandomColor.setText(RANDOM_CHK_LABEL_2);
            return arc;
        }
    }
}