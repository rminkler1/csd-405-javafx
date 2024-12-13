import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LambdaExamples extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {

        StackPane pane = new StackPane();
        pane.getChildren().add(new Text("Hello World"));

        Scene root = new Scene(pane);
        
        stage.setTitle("my Stage");
        stage.setScene(root);
        stage.show();
    }
}
