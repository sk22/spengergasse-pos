package at.spengergasse.kai17521.sem03.the20170130;

import at.spengergasse.kai17521.sem03.the20170130.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class House extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    MyPane root = new MyPane();
    Scene scene = new Scene(root, 1000, 700);
    primaryStage.setScene(scene);
    primaryStage.setTitle("First example");
    primaryStage.show();
  }
}
