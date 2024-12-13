package com.robertminkler.csd405javafx.csd405javafx;
/*
 * Robert Minkler
 * CSD 405 JavaFX Discussion Examples
 * Module 8
 * Nov 27, 2024
 *
 * Show 3 arcs with linear gradients to show how arcs and gradients work
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircleArcExamples extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static LinearGradient getBlueGreenGrad(CycleMethod cycleMethod) {

        // Return a LinearGradient

        // Create gradient stops to define each color and where it should appear along the gradient
        Stop[] bgStops = new Stop[]{new Stop(0,     // offset
                Color.THISTLE                            // color
        ),
                new Stop(.5, Color.TURQUOISE),
                new Stop(1, Color.LIGHTGREEN)
        };

        // Return the gradient
        return new LinearGradient(
                0,                  // x start
                0,                      // y start
                0,                      // x end
                0.5,                    // y end
                true,                   // Use proportional coordinates
                cycleMethod,            // repeat, mirror, or continue last color
                bgStops                 // send stops array
        );
    }

    private static Arc getArc(int xCenter, int yCenter, int width, int height, int arcStart, int arcLength,
                              ArcType arcType, Paint strokeColor, int strokeWidth, Paint arcFill) {

        // Create a new arc including arcType, strokeColor, strokeWidth, and Fill
        Arc arc = new Arc(
                xCenter,    // horizontal position of center
                yCenter,    // vertical position of center
                width,      // radius x - half the arc width
                height,     // radius y - half the arc height
                arcStart,   // start pos. starts at 3 o'clock. positive number is counterclockwise
                arcLength   // length of arc from start pos. positive num is counterclockwise
        );

        // Set remaining attributes
        arc.setType(arcType);               // Choose how the arc should close
        arc.setStroke(strokeColor);         // Set Stroke Color
        arc.setStrokeWidth(strokeWidth);    // Set Stroke Width
        arc.setFill(arcFill);               // Set Fill COLOR from a Paint object

        return arc;
    }

    @Override
    public void start(Stage primaryStage) {

        // Build three arcs using my custom static method and text to display over each arc

        // Arc 1 is OPEN
        Arc arc1 = getArc(150, 150, 75, 100, -45, -260,
                ArcType.OPEN, Color.AQUA, 5, getBlueGreenGrad(CycleMethod.NO_CYCLE));

        Text arc1Label = new Text(100, 275, "ArcType = OPEN\nFill = NO_CYCLE");

        // Arc 2 is CHORD
        Arc arc2 = getArc(300, 150, 75, 100, 45, 260,
                ArcType.CHORD, Color.color(1, .5, 0), 5, getBlueGreenGrad(CycleMethod.REFLECT));

        Text arc2Label = new Text(250, 275, "ArcType = CHORD\nFill = REFLECT");

        // Arc 3 is ROUND
        Arc arc3 = getArc(450, 150, 75, 100, 45, 260,
                ArcType.ROUND, Color.AQUA, 5, getBlueGreenGrad(CycleMethod.REPEAT));

        Text arc3Label = new Text(400, 275, "ArcType = ROUND\nFill = REPEAT");

        // Create a pane to place the arcs in with padding
        Pane pane = new Pane();
        pane.setPadding(new Insets(20));

        // Add all nodes to the pane
        pane.getChildren().addAll(arc1, arc1Label, arc2, arc2Label, arc3, arc3Label);

        // Nest the pane in the scene, set the scene on the stage and show it.
        Scene root = new Scene(pane);
        primaryStage.setTitle("Circle Arc By Robert Minkler");
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
