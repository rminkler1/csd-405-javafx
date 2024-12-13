package com.robertminkler.csd405javafx.csd405javafx;

/*
Robert Minkler
Nov 21, 2024
CSD 405
Module 7 Research Paper Examples

Build a JavaFX application that explores the use of BorderPanel and Accordion that expounds on my paper.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BorderPaneExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // LEFT PANEL ACCORDION
        // Accordions are build from multiple titlePanes

        // VBox example for the TitlePane
        // Build and populate a VBox for later use in a titlePane
        VBox verticalBox = new VBox();
        verticalBox.getChildren().addAll(
                new Label("Vertical Box"),
                new Label("lays out"),
                new Label("elements"),
                new Label("vertically"),
                new Label("These words"),
                new Label("are in an"),
                new Label("VBox")
        );


        // Create each TitledPane for the Accordion
        // The titlePane accepts a String for the title, then a node to reveal.
        // In this example I am using Labels for most Title Panes.
        // I also used a VBox for the VBox pane, and a Text node on the accordion pane.
        TitledPane titledPane1 = new TitledPane("HBox",
                new Label("Horizontal Box lays out elements horizontally. " +
                        "\nThe buttons across the top of this application are in an HBox."));
        TitledPane titledPane2 = new TitledPane("VBox", verticalBox);
        TitledPane titledPane3 = new TitledPane("FlowPlane",
                new Label("Flows items onto the plane horizontally, \nthen wraps around as each row fills up."));
        TitledPane titledPane4 = new TitledPane("BorderPlane",
                new Label("Has sections for top, bottom, left, center, and right. \nUseful for many app layouts." +
                        "\nThis application uses a BorderPlane for the layout."));
        TitledPane titledPane5 = new TitledPane("GridPlane",
                new Label("Lays out elements in a grid."));
        TitledPane titledPane6 = new TitledPane("ScrollPane",
                new Label("Adds scrollable pane for elements that exceed the pane size."));
        TitledPane titledPane7 = new TitledPane("TitledPane",
                new Label("Has a title and a section that expands and collapses. " +
                        "\nThis Accordion is built with eight TitledPanes."));
        TitledPane titledPane8 = new TitledPane("Accordion",
                new Text("This section of collapsable TitledPanes is an Accordion."));


        // Create the Accordion
        Accordion myAccordion = new Accordion();

        // Assign each titled pane to the accordion.
        myAccordion.getPanes().addAll(
                titledPane1,
                titledPane2,
                titledPane3,
                titledPane4,
                titledPane5,
                titledPane6,
                titledPane7,
                titledPane8
        );

        // Set titledPane8 as open when the program opens
        myAccordion.setExpandedPane(titledPane8);

        // TOP PANEL HBOX
        // Build HBox for the top pane filled with buttons
        HBox topBox = new HBox();
        topBox.getChildren().addAll(
                new Button("This"),
                new Button("is"),
                new Button("an"),
                new Button("HBox"),
                new Button("Filled"),
                new Button("With"),
                new Button("Buttons")
        );

        // Set padding, spacing, and alignment for the top HBox buttons
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(5, 5, 5, 5));
        topBox.setSpacing(20);


        // BOTTOM STATUS BAR HBOX
        // Create a new HBox for the bottom status bar
        HBox statusPane = new HBox();

        statusPane.setBorder(new Border(new BorderStroke(
                Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT
        )));
        statusPane.setPadding(new Insets(3, 10, 3, 10));
        statusPane.setSpacing(100);

        // add label text to the status bar
        Label statusText = new Label("This is the bottom pane. It is a great spot for the bottom status bar.");
        statusPane.getChildren().add(statusText);

        // Add additional info to the status bar
        Label moreInfo = new Label("This JavaFX example program written by Robert Minkler");
        statusPane.getChildren().add(moreInfo);

        // BORDERPANE FOR STAGE LAYOUT
        // Create BorderPane
        BorderPane pane = new BorderPane();

        // Set each area of the BorderPane
        pane.setTop(topBox);
        pane.setLeft(myAccordion);
        pane.setCenter(new TextArea("this is the center area."));
        pane.setRight(new Label("This is right."));
        pane.setBottom(statusPane);

        // center the node inside the right pane
        BorderPane.setAlignment(pane.getRight(), Pos.CENTER);
        // add margin around the node in the right pane
        BorderPane.setMargin(pane.getRight(), new Insets(50));

        // Place the BorderPane in a new Scene,
        // set the stage with the current scene,
        // and show the primary stage to open the window.
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Robert Minkler - BorderPanel and Accordion Example");
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
