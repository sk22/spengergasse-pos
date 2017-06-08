/**
 * @author Samuel Kaiser
 * @since 2/3/2017
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Pane root = new ChessPane();
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Chess Pane");
    // primaryStage.setWidth(400);
    // primaryStage.setHeight(400);
    primaryStage.show();
  }
}
