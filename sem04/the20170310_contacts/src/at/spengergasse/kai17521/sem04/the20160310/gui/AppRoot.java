package at.spengergasse.kai17521.sem04.the20160310.gui;

import at.spengergasse.kai17521.sem04.the20160310.control.TheHandle;
import at.spengergasse.kai17521.sem04.the20160310.data.Management;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class AppRoot extends BorderPane {
  private ManagementView managementView;
  private Management management = new Management();

  public AppRoot() {
    TextArea text = new TextArea();
    setPrefSize(600, 600);
    TheHandle handler = new TheHandle(this);
    managementView = new ManagementView(management, handler);
    setTop(new AppMenu(handler));
    setCenter(managementView);
  }

  public ManagementView getManagementView() {
    return managementView;
  }

  public Management getManagement() {
    return management;
  }
}
