package at.spengergasse.kai17521.sem04.the20160310.control;

import at.spengergasse.kai17521.sem04.the20160310.data.Contact;
import at.spengergasse.kai17521.sem04.the20160310.gui.AppRoot;
import at.spengergasse.kai17521.sem04.the20160310.gui.ContactView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class TheHandle implements EventHandler<ActionEvent> {
  private AppRoot root;

  public TheHandle(AppRoot root) {
    this.root = root;
  }

  @Override
  public void handle(ActionEvent event) {
    if (event.getSource() instanceof Node) {
      Node node = (Node) event.getSource();
      if (!(node.getUserData() instanceof String)) return;
      String command = (String) node.getUserData();
      if ("add".equals(command)) {
        Contact contact = root.getManagementView().getContactEditor().build();
        if (contact.getName() == null || contact.getName().isEmpty()) return;
        root.getManagement().add(contact);
        root.getManagementView().getContactsView().getChildren().add(
          new ContactView(contact)
        );
      }
    } else if (event.getSource() instanceof MenuItem) {
      MenuItem item = (MenuItem) event.getSource();
      if (!(item.getUserData() instanceof String)) return;
      String command = (String) item.getUserData();
      try {
        switch (command) {
          case "exit": System.exit(0);
          case "save": root.getManagement().save(); break;
          case "load":
            root.getManagement().load();
            root.getManagementView().reload();
            break;
          case "sortByName":
            root.getManagement().sort(Contact.SORT_BY_NAME);
            root.getManagementView().reload();
            break;
          case "sortByRelation":
            root.getManagement().sort(Contact.SORT_BY_RELATION);
            root.getManagementView().reload();
            break;
          case "sortByDateOfBirth":
            root.getManagement().sort(Contact.SORT_BY_DATE_OF_BIRTH);
            root.getManagementView().reload();
            break;
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
}
