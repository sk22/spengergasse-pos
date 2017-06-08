package at.spengergasse.kai17521.sem04.the20160310.gui;

import at.spengergasse.kai17521.sem04.the20160310.data.Contact;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class ContactView extends TitledPane {
  public ContactView(Contact contact) {
    setText(contact.getName());
    setContent(new HBox(new Text(contact.toString())));
  }
}
