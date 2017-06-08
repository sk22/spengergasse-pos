package at.spengergasse.kai17521.sem04.lab18.task01;

import at.spengergasse.kai17521.sem04.lab18.task01.gui.RootBox;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class CreateButtons extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Button Management Suite 2000");
    Parent root = new RootBox(primaryStage);
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
