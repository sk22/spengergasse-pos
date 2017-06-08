package at.spengergasse.kai17521.sem04.lab15;

import at.spengergasse.kai17521.sem04.lab15.gui.MyPane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

/**
 * @author Samuel Kaiser
 * @since 2/13/2017
 */
public class FileToPaneParser {
  InputStream in;
  private static Map<String, LineParser> parsers = new HashMap<>();

  static {
    parsers.put("button", FileToPaneParser::parseButton);
    parsers.put("label", FileToPaneParser::parseLabel);
    parsers.put("textfield", FileToPaneParser::parseTextField);
  }

  public FileToPaneParser(InputStream in) {
    this.in = in;
  }

  public MyPane parse() {
    MyPane pane = new MyPane();
    try {
      final BufferedReader reader =
        new BufferedReader(new InputStreamReader(in));
      final Stream<Node> nodes =
        reader.lines().map(this::parseLine);
      pane.addAll(nodes.filter(Objects::nonNull).collect(Collectors.toList()));
    } catch (Exception ioe) {
      Label error = new Label(ioe.getMessage());
      error.setTextFill(Color.DARKRED);
      pane.add(error);
      ioe.printStackTrace();
    }
    return pane;
  }

  private Node parseLine(String line) {
    String[] parts = line.split("\\s*,\\s*");
    return parsers.getOrDefault(parts[0].toLowerCase(), (p) -> null)
      .parse(parts);
  }

  /** Button, width, height, x, y, image */
  private static Region parseButton(String[] parts) {
    final Button button = new Button();
    setProperties(button, parts[1], parts[2], parts[3], parts[4]);

    ImageView imageView = new ImageView(new Image(parts[5]));
    imageView.setFitWidth(parseInt(parts[1]));
    imageView.setFitHeight(parseInt(parts[2]));
    button.setGraphic(imageView);
    return button;
  }

  /** Label, contents, width, height, x, y  */
  private static Region parseLabel(String[] parts) {
    final Label label = new Label();
    setProperties(label, parts[2], parts[3], parts[4], parts[5]);

    if (FileToPaneParser.class.getResource("/" + parts[1]) != null) {
      ImageView imageView = new ImageView(new Image(parts[1]));
      imageView.setFitWidth(parseInt(parts[2]));
      imageView.setFitHeight(parseInt(parts[3]));
      label.setGraphic(imageView);
    } else {
      label.setText(parts[1]);
    }
    return label;
  }

  /** TextField, width, height, x, y */
  private static Region parseTextField(String[] parts) {
    final TextField field = new TextField();
    setProperties(field, parts[1], parts[2], parts[3], parts[4]);
    return field;
  }

  private static void setProperties(
    Region node, String width, String height, String x, String y
  ) {
    node.setPrefSize(parseInt(width), parseInt(height));
    node.setLayoutX(parseInt(x));
    node.setLayoutY(parseInt(y));
  }
}
