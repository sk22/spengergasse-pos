package at.spengergasse.kai17521.sem04.lab16.panes;

import at.spengergasse.kai17521.sem04.lab16.panes.gui.WelcomePane;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    ScrollPane welcome = new ScrollPane(new WelcomePane());
    welcome.setFitToWidth(true);
    welcome.setFitToHeight(true);
    ToolBar toolbar = new ToolBar(
      new Label("Welcome Screen Application"),
      new Separator(Orientation.HORIZONTAL),
      new Label("Version -0.2.1231")
    );

    VBox root = new VBox(toolbar, welcome);
    VBox.setVgrow(welcome, Priority.ALWAYS);
    root.setPrefSize(500, 500);
    // root.setBorder(new Border(new BorderStroke(
    //   Color.BLUE, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT
    // )));
    Scene scene = new Scene(root);
    primaryStage.setTitle("Welcome Screen Application");
    primaryStage.setMinWidth(400);
    primaryStage.setMinHeight(400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
