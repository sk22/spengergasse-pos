package at.spengergasse.kai17521.sem04.lab15;

import at.spengergasse.kai17521.sem04.lab15.gui.MyPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 2/13/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    MyPane root = new MyPane();
    FileToPaneParser parser =
      new FileToPaneParser(getClass().getResourceAsStream("/gui-elements.txt"));
    root.add(parser.parse());
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
