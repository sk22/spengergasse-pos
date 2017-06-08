package at.spengergasse.kai17521.sem03.lab04.task02.data;

import java.util.Collections;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 10/10/2016
 */
public class Library extends SongList {
  /**
   * Adds a Song to the list if it doesn't already contain it.
   * @param song The song that should be added
   * @return True if the list did already contain the added Song.
   */
  @Override
  public boolean add(Song song) {
    if (this.songs.contains(song)) {
      return true;
    }
    super.add(song);
    return false;
  }

  /**
   * Add multiple songs to the library
   * @param list List of songs that should be added
   * @return List of added songs. Is less than the given songs if the limit is exceeded
   *         or the Library did already contain some songs.
   */
  public List<Song> addAll(List<Song> list) {
    list.removeAll(Collections.<Song>singleton(null));
    List<Song> sub;
    if (this.limit > 0 && this.songs.size() + list.size() > this.limit) {
      sub = list.subList(0, this.limit - this.songs.size());
    } else {
      sub = list;
    }
    sub.removeIf(song -> songs.contains(song));
    this.songs.addAll(sub);
    return sub;
  }
}
