package com.robertminkler.csd405javafx.csd405javafx;

/*
 * Robert Minkler
 * Intermediate Java CSD 405
 * Module 10 Discussion - Disabled Forms
 * Dec 4, 2024
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisabledFormExamples extends Application {

    // Constants
    final int MIN_LENGTH = 8;       // password length where the sign-in button is enabled
    final String[] HARDWARE_MODELS = {"Laptop", "Desktop", "Server"};
    final String[] LAPTOP_MODELS = {"Small Laptop 1234", "Medium Laptop 45678", "Large Laptop 09876"};
    final String[] SERVER_MODELS = {"Small Server 5678", "Medium Server 4321", "Large Server 34567"};
    final String[] DESKTOP_MODELS = {"Small Desktop 90123", "Medium Desktop 0987", "Large Desktop 3456"};
    final int GAP = 10;
    final int MARGIN = 25;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // FORM 1 LOGIN WITH DISABLED BUTTON
        // BUTTON IS ENABLED AFTER EIGHT CHARACTERS ARE ENTERED IN THE PASSWORD FIELD
        // We should not do this because the user has no feedback telling them why the
        // button is disabled.

        // Login form from
        // https://docs.oracle.com/javafx/2/get_started/form.htm

        // Setup gridPane for login form
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(GAP);
        gridPane.setVgap(GAP);
        gridPane.setPadding(new Insets(MARGIN));

        // Title
        Text sceneTitle = new Text("Form 1 Sign-in");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);

        // Username label and field
        Label userName = new Label("User Name:");
        gridPane.add(userName, 0, 1);
        TextField userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        // Password label and field
        Label pw = new Label("Password:");
        gridPane.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        gridPane.add(pwBox, 1, 2);

        // Text showing sign in pressed placeholder
        Text actionTarget = new Text();
        actionTarget.setFill(Color.RED);
        gridPane.add(actionTarget, 1, 6);

        // Sign-in Button lower right - Disabled
        Button btn = new Button("Sign in");
        btn.setOnAction(_ -> actionTarget.setText("Sign in Button Pressed."));
        btn.setDisable(true);
        HBox hbBtn = new HBox(GAP);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        gridPane.add(hbBtn, 1, 4);

        // Bind btn disabled to the password length
        btn.disableProperty().bind(
                pwBox.textProperty().length().lessThan(MIN_LENGTH));


        // FORM 2 DROP-DOWN LISTS
        // The second drop-down list is disabled and has no values until the first drop-down is selected.
        // This is a reasonable use of a disabled form because we don't want the user selecting this
        // value until the first list selection is set.

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(MARGIN));
        vBox.setSpacing(GAP);

        // Title
        Text form2Title = new Text("Form 2 Drop-Down\nSelect your model");
        form2Title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        vBox.getChildren().add(form2Title);


        // First drop-down menu
        ComboBox<String> hardwareTypeSelector = new ComboBox<>(FXCollections.observableArrayList(HARDWARE_MODELS));
        hardwareTypeSelector.setPrefWidth(200);
        vBox.getChildren().add(hardwareTypeSelector);

        // Second drop-down menu created and disabled
        ComboBox<String> modelSelector = new ComboBox<>();
        modelSelector.setPrefWidth(200);
        modelSelector.setDisable(true);
        vBox.getChildren().add(modelSelector);

        // Link event action for the hardwareTypeSelector to control the modelSelector
        hardwareTypeSelector.setOnAction(_ -> setComboBox2(hardwareTypeSelector, modelSelector));


        // Place both forms in HBox
        HBox hBox = new HBox();
        hBox.getChildren().addAll(gridPane, vBox);

        // Set scene and show it
        Scene root = new Scene(hBox);
        primaryStage.setTitle("Disabled Form Examples By Robert Minkler");
        primaryStage.setScene(root);
        primaryStage.show();

    }

    private void setComboBox2(ComboBox<String> sourceComboBox, ComboBox<String> targetComboBox) {

        // On an action event, check the selection of the source drop-down.
        // Set the second drop-down menu accordingly and enable it

        if (sourceComboBox.getValue().equals("Laptop")) {
            targetComboBox.setItems(FXCollections.observableArrayList(LAPTOP_MODELS));
            targetComboBox.setDisable(false);

        } else if (sourceComboBox.getValue().equals("Desktop")) {
            targetComboBox.setItems(FXCollections.observableArrayList(DESKTOP_MODELS));
            targetComboBox.setDisable(false);

        } else if (sourceComboBox.getValue().equals("Server")) {
            targetComboBox.setItems(FXCollections.observableArrayList(SERVER_MODELS));
            targetComboBox.setDisable(false);

        } else {
            // If no valid option is selected, disable the box
            targetComboBox.setDisable(true);
        }
    }
}
