package at.spengergasse.kai17521.sem04.lab18.task01.gui;

import at.spengergasse.kai17521.sem04.lab18.task01.control.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
class SelfRemovingButton extends Button {
  SelfRemovingButton(String text, Image image, Controller controller) {
    setText(text);
    setOnAction(event -> controller.remove(this));

    if (image != null) {
      ImageView imageView = new ImageView(image);
      imageView.setPreserveRatio(true);
      imageView.setFitWidth(100);
      setGraphic(imageView);
    }
  }

  SelfRemovingButton(String text, Controller controller) {
    this(text, null, controller);
  }
}
