package com.robertminkler.csd405javafx.csd405javafx;

/*
 *  Robert Minkler
 *  CSD 405 Module 10 - Cards
 *  Dec 5, 2024
 *
 * Display four random cards from a deck of 52. Draw each card only once.
 * Below the cards is a refresh button that displays four new cards.
 * Cards shuffle after 40 cards have been selected from the deck.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Cards2 extends Application {

    // Set Logic Constants
    private static final int NUM_CARDS_TO_SHOW = 4;    // How many cards to display
    private static final int CARDS_AVAIL = 52;         // Select any card except Jokers
    private static final String CARD_DIR = "cards/";   // Directory where the card images are stored
    private static final int SHUFFLE_POINT = 40;       // Shuffle the deck after this many cards drawn

    // Track what cards were selected in a list
    private final List<Integer> selectedCards = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Set layout constants
        int PADDING = 15;
        Insets PADDING_INSETS = new Insets(PADDING);
        int GAP = 10;
        int CARD_WIDTH = 72;    // card width in pixels


        // Setup FlowPane to hold the cards
        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(PADDING_INSETS);
        cardPane.setHgap(GAP);
        cardPane.setVgap(GAP);

        // Calculate cardPane width based on cards to display
        // card width in px
        cardPane.setPrefWidth(GAP * (NUM_CARDS_TO_SHOW - 1) + NUM_CARDS_TO_SHOW * CARD_WIDTH + PADDING * 2);

        // select the random cards and display them
        selectRandomCards(cardPane);

        // Create a refresh button that deals new cards on click
        Button refreshButton = new Button("Draw new cards");
        refreshButton.setOnMouseClicked(_ -> getNewCards(cardPane));


        // Place the button in a pane
        StackPane buttonPane = new StackPane();
        buttonPane.setPadding(PADDING_INSETS);
        buttonPane.getChildren().add(refreshButton);

        // Create a borderPane for the button layout
        BorderPane borderPane = new BorderPane();

        // assign panes to their places
        borderPane.setCenter(cardPane);
        borderPane.setBottom(buttonPane);


        // Display the cards on screen
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getNewCards(FlowPane cardPane) {
        // Clear all cards from the table
        cardPane.getChildren().clear();

        // shuffle the deck if shuffle point was reached
        shuffleDeck();

        // get and display four new cards
        selectRandomCards(cardPane);
    }

    private void selectRandomCards(FlowPane pane) {

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
    }


    private ImageView getRandCard() throws FileNotFoundException {
        // Return ImageView with random card 1-52

        int randNum;    // track randNum in this scope

        // pick random number between 1 and maxCard
        // Pick another number if the card has already been picked
        do {
            randNum = (int) (Math.random() * CARDS_AVAIL + 1);
        } while (selectedCards.contains(randNum));

        // add the randNum to the list of picked numbers so we don't pick it again
        selectedCards.add(randNum);

        // Get the card image and return the ImageView
        FileInputStream cardLoc = new FileInputStream(CARD_DIR + randNum + ".png");
        Image card = new Image(cardLoc);
        return new ImageView(card);
    }

    private void shuffleDeck() {

        // If 40 cards have been selected, shuffle the deck
        // by removing all values from selectedCards
        if (selectedCards.size() >= SHUFFLE_POINT) {
            selectedCards.clear();
        }
    }
}