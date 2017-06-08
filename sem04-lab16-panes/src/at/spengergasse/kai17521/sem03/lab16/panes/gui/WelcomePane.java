package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
public class WelcomePane extends BorderPane {
  public WelcomePane() {
    setPadding(new Insets(30));
    // setBorder(new Border(new BorderStroke(
    //   Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT
    // )));

    // Title Bar
    TitleBar titleBar = new TitleBar();
    setMargin(titleBar, new Insets(0, 0, 10, 0));
    setTop(titleBar);

    // Content
    ContentBox contentBox = new ContentBox();
    setCenter(contentBox);
  }
}
