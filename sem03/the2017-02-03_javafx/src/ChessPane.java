import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Samuel Kaiser
 * @since 2/3/2017
 */
public class ChessPane extends Pane {
  public ChessPane() {
    init();
  }

  private void init() {
    setPrefSize(400, 400);
    boolean boo = false;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (x == 0) boo = !boo;
        Rectangle rect = getRectangle(boo);
        rect.setLayoutX(x * 50);
        rect.setLayoutY(y * 50);
        getChildren().add(rect);
        boo = !boo;
      }
    }
  }

  private Rectangle getRectangle(boolean dark) {
    Rectangle rect = new Rectangle(50, 50);
    rect.setFill(dark ? Color.BLACK : Color.WHITE);
    return rect;
  }
}
