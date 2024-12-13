package com.robertminkler.csd405javafx.csd405javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * JavaFX Calendar: Displays the current month.
 */
public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Get current month and year
        LocalDate today = LocalDate.now();
        YearMonth yearMonth = YearMonth.of(today.getYear(), today.getMonth());
        int daysInMonth = yearMonth.lengthOfMonth();
        String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int firstDayOfWeek = yearMonth.atDay(1).getDayOfWeek().getValue(); // Monday = 1, Sunday = 7

        // Header with month and year
        Label header = new Label(monthName + " " + today.getYear());
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create grid for the calendar
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Add day labels
        String[] weekDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int col = 0; col < weekDays.length; col++) {
            Label label = new Label(weekDays[col]);
            label.setStyle("-fx-font-weight: bold;");
            grid.add(label, col, 0);
        }

        // Add blank labels for days before the first of the month
        int row = 1, col = firstDayOfWeek - 1;
        for (int day = 1; day <= daysInMonth; day++) {
            Label label = new Label(String.valueOf(day));
            label.setPadding(new Insets(5));
            grid.add(label, col, row);
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }

        // Layout the calendar
        VBox layout = new VBox(10, header, grid);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("JavaFX Calendar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
