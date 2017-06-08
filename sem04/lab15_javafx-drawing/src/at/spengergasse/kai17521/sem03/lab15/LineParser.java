package at.spengergasse.kai17521.sem04.lab15;

import com.sun.istack.internal.NotNull;
import javafx.scene.Node;

/**
 * @author Samuel Kaiser
 * @since 2/13/2017
 */
@FunctionalInterface
public interface LineParser {
  public Node parse(@NotNull String[] parts);
}
