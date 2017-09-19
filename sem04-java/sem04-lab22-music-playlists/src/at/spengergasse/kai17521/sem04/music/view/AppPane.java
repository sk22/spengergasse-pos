package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.controller.AppController;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

/**
 * @author Samuel Kaiser
 * @since 5/10/2017
 */
public class AppPane extends BorderPane {
  public AppPane(AppController controller) {
    setTop(new AppMenuBar(controller));
    setCenter(new MainPane(controller));
    setBottom(new ControlsPane(controller));
  }
}
