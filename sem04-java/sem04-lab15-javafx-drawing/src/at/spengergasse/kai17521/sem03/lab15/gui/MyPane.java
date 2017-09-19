package at.spengergasse.kai17521.sem04.lab15.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 2/13/2017
 */
public class MyPane extends Pane {
  public MyPane() {
    setPrefSize(500, 500);
  }

  public void addAll(List<Node> nodes) {
    getChildren().addAll(nodes);
  }

  public void add(Node node) {
    getChildren().add(node);
  }
}
