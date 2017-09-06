package at.spengergasse.kai17521.sem03.the20170130.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.paint.Color.*;

/**
 * @author Samuel Kaiser
 * @since 1/30/2017
 */
public class MyPane extends Pane {
  public MyPane() {
    init();
  }

  private void init() {
    Circle sun = new Circle(50, YELLOW);
    sun.setCenterX(100);
    sun.setCenterY(100);
    getChildren().addAll(sun);

    List<Line> rays = new ArrayList<>();
    rays.addAll(Arrays.asList(
      new Line(100, 0, 100, 200),
      new Line(0, 100, 200, 100),
      new Line(10, 10, 180, 180),
      new Line(10, 180, 180, 10)
    ));

    rays.forEach(this::setRayProperties);
    getChildren().addAll(rays);

    Rectangle house = new Rectangle(200, 200, INDIANRED);
    house.setLayoutX(300);
    house.setLayoutY(300);

    Rectangle window = new Rectangle(50, 50, BROWN);
    window.setLayoutX(330);
    window.setLayoutY(350);

    Rectangle door = new Rectangle(50, 100, BROWN);
    door.setLayoutX(420);
    door.setLayoutY(400);

    Polygon roof = new Polygon(290, 300, 400, 250, 510, 300);
    getChildren().addAll(house, door, window, roof);


    // System.out.println(getClass().getResource("snail.png").toExternalForm());

    Image image = new Image("snail.png");
    ImageView imageView = new ImageView(image);
    imageView.setLayoutY(300);
    imageView.setScaleX(0.5);
    imageView.setScaleY(0.5);
    getChildren().add(imageView);
  }

  private void setRayProperties(Line ray) {
    ray.setStroke(YELLOW);
    ray.setStrokeWidth(5);
  }
}
