package at.spengergasse.kai17521.sem04.lab21.model;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Samuel Kaiser
 * @since 1/20/2017
 */
interface SongCollection {
  /**
   * Returns the intersect between the parameter and this object
   * @param songs Collection of Songs
   * @return Returns all songs that both given collection and SongCollection
   *         contain
   */
  Collection<Song> overlap(Collection<Song> songs);

  /**
   * Filters all songs using a predicate
   * @param predicate Filter
   * @return Filtered songs
   */
  Collection<Song> filter(Predicate<Song> predicate);

  /**
   * Removes a list of songs
   * @param songs Collection of songs that should be removed
   * @return The songs the SongCollection contained
   * @see #overlap
   */
  Collection<Song> remove(Collection<Song> songs);

  /**
   * Returns the first result that matches the predicate
   * @param predicate Filter
   * @return First song that matches
   * @see #filter
   */
  Song filterOne(Predicate<Song> predicate);
}
