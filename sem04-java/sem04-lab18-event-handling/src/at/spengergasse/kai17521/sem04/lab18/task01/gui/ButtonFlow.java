package at.spengergasse.kai17521.sem04.lab18.task01.gui;

import at.spengergasse.kai17521.sem04.lab18.task01.control.Controller;
import javafx.scene.layout.FlowPane;

/**
 * @author Samuel Kaiser
 * @since 3/6/2017
 */
public class ButtonFlow extends FlowPane {
  ButtonFlow(Controller controller) {
    controller.setPane(this);
    setHgap(5);
    setVgap(5);
  }
}
