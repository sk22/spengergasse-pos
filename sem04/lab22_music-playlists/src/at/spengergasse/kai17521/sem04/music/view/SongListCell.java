package at.spengergasse.kai17521.sem04.music.view;

import at.spengergasse.kai17521.sem04.music.model.Song;
import javafx.scene.control.ListCell;

/**
 * @author Samuel Kaiser
 * @since 5/11/2017
 */
class SongListCell extends ListCell<Song> {
  @Override
  protected void updateItem(Song item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      textProperty().unbind();
      setText(null);
    } else {
      textProperty().bind(item.stringProperty());
    }
  }
}
