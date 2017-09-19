package at.spengergasse.kai17521.sem04.lab19.view;

import at.spengergasse.kai17521.sem04.lab19.controller.Handlers;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class AppRoot extends BorderPane {
  private Stage stage;
  private Handlers handlers = new Handlers(this);
  private Adders adders = new Adders(handlers);
  private Pane artboard = new Pane();
  private LogTextArea logger = new LogTextArea();

  public AppRoot(Stage stage) {
    this.stage = stage;
    setTop(new AppMenuBar(handlers));
    setBottom(logger);
    setCenter(artboard);
    setLeft(adders);
  }

  public Adders getAdders() {
    return adders;
  }

  public Pane getArtboard() {
    return artboard;
  }


  public LogTextArea getLogger() {
    return logger;
  }

  public Stage getStage() {
    return stage;
  }
}
