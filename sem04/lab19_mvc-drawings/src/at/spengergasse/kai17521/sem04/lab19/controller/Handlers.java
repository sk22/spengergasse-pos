package at.spengergasse.kai17521.sem04.lab19.controller;

import at.spengergasse.kai17521.sem04.lab19.model.DrawableCircle;
import at.spengergasse.kai17521.sem04.lab19.model.DrawableRectangle;
import at.spengergasse.kai17521.sem04.lab19.model.DrawableShape;
import at.spengergasse.kai17521.sem04.lab19.model.ShapeList;
import at.spengergasse.kai17521.sem04.lab19.view.AppRoot;
import at.spengergasse.kai17521.sem04.lab19.view.CircleAdder;
import at.spengergasse.kai17521.sem04.lab19.view.RectangleAdder;
import at.spengergasse.kai17521.sem04.lab19.view.ShapeAdder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class Handlers {
  private AppRoot root;
  private ShapeList list = new ShapeList();

  public Handlers(AppRoot root) {
    this.root = root;
  }

  private void alertFor(Exception e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setContentText(e.toString());
    alert.show();
  }

  public EventHandler<ActionEvent> fileHandler = event -> {
    if (!(event.getSource() instanceof MenuItem)) return;
    MenuItem item = (MenuItem) event.getSource();
    if (!(item.getUserData() instanceof String)) return;
    String type = (String) item.getUserData();

    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
      "Serialization", "*.ser"
    ));

    try {
      switch (type) {
        case "saveAs": saveAs(chooser); break;
        case "open": open(chooser); break;
        case "save": save(chooser); break;
      }
    } catch (Exception e) {
      alertFor(e);
    }
  };

  private void saveAs(FileChooser chooser) throws Exception {
    list.saveTo(chooser.showSaveDialog(root.getStage()));
  }

  private void save(FileChooser chooser) throws Exception {
    if (!list.save()) {
      list.saveTo(chooser.showSaveDialog(root.getStage()));
    }
  }

  private void open(FileChooser chooser) throws Exception {
    list.open(chooser.showOpenDialog(root.getStage()));
    root.getArtboard().getChildren().clear();
    list.forEach(ds -> root.getArtboard().getChildren().add(ds.draw()));
    root.getLogger().clear();
  }

  public EventHandler<ActionEvent> addHandler = event -> {
    ShapeAdder adder = root.getAdders().getCurrent();
    DrawableShape shape = null;

    try {
      if (adder instanceof CircleAdder) {
        CircleAdder circleAdder = (CircleAdder) adder;
        shape = new DrawableCircle(
          circleAdder.colorPicker.getValue().toString(),
          Double.parseDouble(circleAdder.xField.getText()),
          Double.parseDouble(circleAdder.yField.getText()),
          Double.parseDouble(circleAdder.radiusField.getText())
        );
      }

      if (adder instanceof RectangleAdder) {
        RectangleAdder rectangleAdder = (RectangleAdder) adder;
        shape = new DrawableRectangle(
          rectangleAdder.colorPicker.getValue().toString(),
          Double.parseDouble(rectangleAdder.xField.getText()),
          Double.parseDouble(rectangleAdder.yField.getText()),
          Double.parseDouble(rectangleAdder.widthField.getText()),
          Double.parseDouble(rectangleAdder.heightField.getText())
        );
      }
    } catch (Exception e) {
      alertFor(e);
    }


    if (shape == null) return;

    list.add(shape);
    root.getLogger().println(shape.toString());
    root.getArtboard().getChildren().add(shape.draw());
  };

  public EventHandler<ActionEvent> clearHandler = event -> {
    root.getArtboard().getChildren().clear();
    root.getLogger().clear();
    list.clear();
  };
}
