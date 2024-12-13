package com.robertminkler.mediatest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMediaPlayer extends Application {

    private static final String MEDIA_URL = "http://liveexample.pearsoncmg.com/common/sample.mp4";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Similar to adding an image, we can add video or audio by creating
        // Media, MediaPlayer, and MediaView objects to place in a pane.
        Media video = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(video);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button btnPlay = new Button(">");
        btnPlay.setOnAction(_ -> {
            if (btnPlay.getText().equals(">")) {
                mediaPlayer.play();
                btnPlay.setText("||");
            } else {
                mediaPlayer.pause();
                btnPlay.setText(">");
            }
        });

        Button btnRewind = new Button("<<");
        btnRewind.setOnAction(_ -> mediaPlayer.seek(Duration.ZERO));

        Slider sldVolume = new Slider();
        sldVolume.setPrefWidth(150);
        sldVolume.setMaxWidth(Region.USE_PREF_SIZE);
        sldVolume.setMinWidth(30);
        sldVolume.setValue(50);

        mediaPlayer.volumeProperty().bind(sldVolume.valueProperty().divide(100));

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btnPlay, btnRewind, new Label("Volume"), sldVolume);

        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 800, 520);
        primaryStage.setTitle("media Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
