package com.robertminkler.csd405javafx.csd405javafx;

/*
 *  Robert Minkler
 *  CSD 405 Module 8 - Cards
 *  Nov 27, 2024
 *
 * Display four random cards from a deck of 52. Draw each card only once.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Cards extends Application {

    // Constants
    private final int NUM_CARDS_TO_SHOW = 4;    // How many cards to display
    private final int CARDS_AVAIL = 52;         // Select any card except Jokers
    private final String CARD_DIR = "cards/";   // Directory where the card images are stored

    // Track what cards were selected in a list
    private final List<Integer> pickedNumbers = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int PADDING = 15;
        int GAP = 10;
        int CARD_WIDTH = 72;    // card width in pixels


        // Setup FlowPane
        FlowPane pane = new FlowPane();
        // pane padding
        pane.setPadding(new Insets(PADDING));
        // gap between nodes

        pane.setHgap(GAP);
        pane.setVgap(GAP);

        // Calculate pane width based on cards to display
        // card width in px
        pane.setPrefWidth(GAP * (NUM_CARDS_TO_SHOW - 1) + NUM_CARDS_TO_SHOW * CARD_WIDTH + PADDING * 2);

        // Get (numCardsToShow) random cards and add them to the FlowPlane
        for (int i = 0; i < NUM_CARDS_TO_SHOW; i++) {

            try {

                // Get a random card and add it to the pane
                pane.getChildren().add(getRandCard());

            } catch (FileNotFoundException ex) {

                ex.printStackTrace();

                // Display File Not Found in place of the card
                pane.getChildren().add(new Label("File \nNot \nFound"));
            }
        }

        // Display the cards on screen
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private ImageView getRandCard() throws FileNotFoundException {
        // Return ImageView with random card 1-52

        int randNum;    // track randNum in this scope

        // pick random number between 1 and maxCard
        // Pick another number if the card has already been picked
        do {
            randNum = (int) (Math.random() * CARDS_AVAIL + 1);
        } while (pickedNumbers.contains(randNum));

        // add the randNum to the list of picked numbers so we don't pick it again
        pickedNumbers.add(randNum);

        // Get the card image and return the ImageView
        FileInputStream cardLoc = new FileInputStream(CARD_DIR + randNum + ".png");
        Image card = new Image(cardLoc);
        return new ImageView(card);
    }
}