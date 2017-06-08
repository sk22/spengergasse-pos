package at.spengergasse.kai17521.sem04.lab21.model;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * SongList that holds songs
 * @author Samuel Kaiser
 * @since 09/19/2016
 */
public class SongList extends ArrayList<Song> implements SongCollection, Serializable {
  /**
   * Add a single Song to the list.
   * The Song is also added if it is already contained by the list.
   * @param song Song that should be added (can be added multiple times)
   * @return True if the added Song didn't exist in the SongList before.
   */
  public boolean add(Song song) throws IndexOutOfBoundsException {
    if (song == null) {
      throw new IllegalArgumentException("Song must not be null.");
    }
    boolean newSong = !contains(song);
    return super.add(song) && newSong;
  }

  @Override
  public List<Song> overlap(Collection<Song> songs) {
    List<Song> result = new ArrayList<>();
    songs.forEach(song -> { if (contains(songs)) result.add(song); });
    return result;
  }

  @Override
  public Collection<Song> filter(Predicate<Song> predicate) {
    return stream().filter(predicate).collect(Collectors.toList());
  }

  @Override
  public Song filterOne(Predicate<Song> predicate) {
    return stream().filter(predicate).findAny().orElse(null);
  }

  @Override
  public Collection<Song> remove(Collection<Song> songs) {
    Collection<Song> overlap = overlap(songs);
    removeAll(overlap);
    return overlap;
  }

  /**
   * Reverses the order of all songs the library has
   */
  public void reverse() {
    Collections.reverse(this);
  }

  @Override
  public String toString() {
    return size() + " Songs\n" + stream()
      .map(Song::toString)
      .reduce("", (prev, curr) -> prev + "- " + curr + "\n");
  }

  public void print() {
    System.out.println(toString());
  }

  public int count() {
    return size();
  }
}
