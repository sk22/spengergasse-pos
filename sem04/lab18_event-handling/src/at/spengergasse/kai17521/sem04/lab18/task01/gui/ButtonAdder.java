package at.spengergasse.kai17521.sem04.lab18.task01.gui;

import at.spengergasse.kai17521.sem04.lab18.task01.control.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
class ButtonAdder extends HBox {
  ButtonAdder(Controller controller, Window window) {
    setSpacing(10);

    TextField buttonName = new TextField();
    setHgrow(buttonName, Priority.ALWAYS);
    Button newButton = new Button("New Button");

    EventHandler<ActionEvent> newButtonHandler = event -> {
      controller.add(new SelfRemovingButton(buttonName.getText(), controller));
      buttonName.clear();
    };

    buttonName.setOnAction(newButtonHandler);
    newButton.setOnAction(newButtonHandler);
    Button newImageButton = new Button("New Image Button");
    newImageButton.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
        "Image Files", "*.png", "*.jpg", "*.gif"
      ));
      File file = fileChooser.showOpenDialog(window);
      if (file == null) return;
      try {
        Image image = new Image(new FileInputStream(file));
        controller.add(new SelfRemovingButton(
          buttonName.getText(), image, controller
        ));
        buttonName.clear();
      } catch (FileNotFoundException e) {
        System.err.println(e.getMessage());
      }
    });

    getChildren().addAll(buttonName, newButton, newImageButton);
  }
}
