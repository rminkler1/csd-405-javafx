package com.robertminkler.csd405javafx.csd405javafx;

/*
 * Robert Minkler
 * Nov 30, 2024
 * CSD 405 Module 9 Assignment Program
 * Research Lambda Functions
 *
 * In the main function I demonstrate how to build a custom lambda function that outputs to the console.
 * Then I use a lambda function to print each item in an ArrayList using the forEach() method.
 *
 * Then we jump to the JavaFX GUI application where button actions are controlled by lambda functions.
 *
 */

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LambdaExamples extends Application {

    public static void main(String[] args) {

        // lambda expression that sends a DoerOfThing object to the whatToDo method.
        // What it does is print to the console
        // With the lambda, we are able to define the class and method concisely.
        whatToDo(
                () -> System.out.println("Custom Lambda function does what I choose. \n" +
                        "What shall we do? Let's print this text.")    // lambda
        );


        // The ArrayList collection can accept an anonymous function or lambda in various methods
        // The forEach() method is one such example.
        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(11);
        myList.add(12);

        // Determine what to do for each element in the list by using a lambda
        System.out.println("Print each Integer in myList.");
        myList.forEach(n -> System.out.println(n));


        // Launch JavaFX app
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        // create a circle shape and buttons to place in the scene
        Circle colorfulCircle = new Circle();
        colorfulCircle.setRadius(200);

        // Create buttons and assign them an ID
        Button redUp = new Button("Red Up");
        redUp.setId("RU");
        Button redDn = new Button("Red Down");
        redDn.setId("RD");
        Button grnUp = new Button("Green Up");
        grnUp.setId("GU");
        Button grnDn = new Button("Green Down");
        grnDn.setId("GD");
        Button bluUp = new Button("Blue Up");
        bluUp.setId("BU");
        Button bluDn = new Button("Blue Down");
        bluDn.setId("BD");

        // Lambda functions control the action of each button.
        // In this case they trigger a method to perform a more complex task of changing the circle color
        redUp.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));
        redDn.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));
        grnUp.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));
        grnDn.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));
        bluUp.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));
        bluDn.setOnAction(e -> chgShapeFillColor(colorfulCircle, e));


        // place label color at the top
        Label showColor = new Label(colorfulCircle.getFill().toString());

        // bind the circle fill with the label text
        StringProperty op = showColor.textProperty();
        op.bind(colorfulCircle.fillProperty().asString());

        // Build a scene and place all nodes in it
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20));

        StackPane labelPane = new StackPane();
        labelPane.getChildren().add(showColor);

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(redUp, redDn, grnUp, grnDn, bluUp, bluDn);

        pane.setTop(labelPane);
        pane.setCenter(colorfulCircle);
        pane.setBottom(bottomBox);

        Scene root = new Scene(pane, 500, 500);
        stage.setTitle("Color Changing Circle by Robert Minkler");
        stage.setScene(root);
        stage.show();
    }

    // Define FI for the whatToDo method
    @FunctionalInterface
    interface DoerOfThing {
        void doIt();
    }

    // Define a method that accepts a DoerOfThing object and runs it's doIt method.
    private static void whatToDo(DoerOfThing doer) {
        doer.doIt();
    }


    // The remaining methods are used to change the shape fill color

    // changes the color of a shape determined by the ID of the event triggering object
    private void chgShapeFillColor(Shape shape, ActionEvent event) {

        // get the RGB color of the shape as a double
        double red = ((Color) shape.getFill()).getRed();
        double green = ((Color) shape.getFill()).getGreen();
        double blue = ((Color) shape.getFill()).getBlue();

        // Code Inspired by "Hamburg is nice" at
        // https://stackoverflow.com/questions/35676519/actionevent-get-source-of-button-javafx

        // Get Calling Button ID
        String id = ((Node) event.getSource()).getId();

        // Use Button ID to choose which action to take
        switch (id) {
            case "RU":
                red = this.colorUp(red);
                break;
            case "RD":
                red = this.colorDn(red);
                break;
            case "GU":
                green = this.colorUp(green);
                break;
            case "GD":
                green = this.colorDn(green);
                break;
            case "BU":
                blue = this.colorUp(blue);
                break;
            case "BD":
                blue = this.colorDn(blue);
                break;
        }

        // set the shape color
        shape.setFill(Color.color(red, green, blue));

    }

    // shift the color up
    private double colorUp(double colorDbl) {
        return (colorDbl < 0.97) ? colorDbl + 0.03 : 1.0;
    }

    // shift the color down
    private double colorDn(double colorDbl) {
        return (colorDbl > 0.03) ? colorDbl - 0.03 : 0.0;
    }
}