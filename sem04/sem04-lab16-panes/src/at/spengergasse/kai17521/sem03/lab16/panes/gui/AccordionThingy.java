package at.spengergasse.kai17521.sem04.lab16.panes.gui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * @author Samuel Kaiser
 * @since 2/20/2017
 */
class AccordionThingy extends Accordion {
  AccordionThingy() {
    VBox thing1 = new VBox(new Label("Welcome!"));
    VBox thing2 = new VBox(new Label("Have a nice day."));
    VBox thing3 = new VBox(new Label("Good to see you!"));

    getPanes().addAll(
      new TitledPane("First welcome thingy", thing1),
      new TitledPane("Second welcome thingy", thing2),
      new TitledPane("Third welcome thingy", thing3)
    );
  }
}
