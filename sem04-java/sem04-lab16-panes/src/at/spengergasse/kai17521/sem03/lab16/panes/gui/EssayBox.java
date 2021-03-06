package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Samuel Kaiser
 * @since 2/22/2017
 */
class EssayBox extends VBox {
  EssayBox() {
    TextArea area = new TextArea("Lorem ipsum dolor sit amet, consectetur " +
      "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
      "magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
      "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute " +
      "irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
      "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
      "proident, sunt in culpa qui officia deserunt mollit anim id est " +
      "laborum. Sed ut perspiciatis unde omnis iste natus error sit " +
      "voluptatem accusantium doloremque laudantium, totam rem aperiam, " +
      "eaque ipsa quae ab illo inventore veritatis et quasi architecto " +
      "beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia " +
      "voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur " +
      "magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro " +
      "quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, " +
      "adipisci velit, sed quia non numquam eius modi tempora incidunt ut " +
      "labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima " +
      "veniam, quis nostrum exercitationem ullam corporis suscipit " +
      "laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel " +
      "eum iure reprehenderit qui in ea voluptate velit esse quam nihil " +
      "molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas " +
      "nulla pariatur?");
    setMargin(area, new Insets(3, 0, 0, 0));
    area.setWrapText(true);
    VBox.setVgrow(area, Priority.ALWAYS);
    // setBorder(
    //   new Border(new BorderStroke(Color.BLACK,
    //   BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT))
    // );
    getChildren().addAll(
      new Label("Write an essay or something cause why not"),
      area
    );
  }
}
