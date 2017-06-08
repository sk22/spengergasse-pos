package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
class ContentBox extends VBox {
  ContentBox() {
    setSpacing(20);
    EssayBox essayBox = new EssayBox();
    setVgrow(essayBox, Priority.ALWAYS);
    // setBorder(new Border(new BorderStroke(
    //   Color.RED, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT
    // )));
    getChildren().addAll(
      new BorderedText(),
      new RandomControls(),
      new AccordionThingy(),
      essayBox
    );
  }
}
