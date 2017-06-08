package data;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Library that holds songs
 * @author Samuel Kaiser
 * @since 09/19/2016
 */
public class Library {
  private List<Song> songs;
  private int limit;

  /**
   * Creates library with a limit
   * @param limit Limit of the number of songs that the list can have. Pass -1 for no limit
   */
  public Library(int limit) {
    this.songs = new ArrayList<>();
    setLimit(limit);
  }

  /**
   * Creates library without limit
   */
  public Library() {
    this(-1);
  }

  /**
   * Add a single song to the library
   * @param song The song that should be added
   * @return Song that was added or null if limit is reached
   */
  public Song add(Song song) {
    if (song == null) throw new IllegalArgumentException("Song must not be null.");
    if (songs.size() >= limit && limit >= 0) {
      return null;
    }
    songs.add(song);
    return song;
  }

  /**
   * Add multiple songs to the library
   * @param list List of songs that should be added
   * @return List of added songs. Can be less then the given songs if limit is exceeded
   */
  public List<Song> addAll(List<Song> list) {
    list.removeAll(Collections.<Song>singleton(null));
    List<Song> sub;
    if (this.limit > 0 && this.songs.size() + list.size() > this.limit) {
      sub = list.subList(0, this.limit - this.songs.size());
    } else {
      sub = list;
    }
    this.songs.addAll(sub);
    return sub;
  }

  /**
   * Pass an array or a variable number of songs that should be added
   * @param args Songs
   * @return List of added songs. Can be less then the given songs if limit is exceeded
   */
  public List<Song> addAll(Song... args) {
    List<Song> list = new ArrayList<>(Arrays.asList(args));
    return this.addAll(list);
  }

  /**
   * Returns all given songs that the library contains
   * @param list List to check
   * @return List of all songs that the given list and the library contain
   */
  public List<Song> overlap(List<Song> list) {
    List<Song> result = new ArrayList<>();
    list.forEach(song -> { if (this.songs.contains(song)) result.add(song); });
    return result;
  }

  /**
   * Returns a list of all songs where given fields match. All other fields must be null.
   * @param title  Title
   * @param artist Artist
   * @param album  Album
   * @param year   Year
   * @param genre  Genre
   * @return List of songs
   */
  public List<Song> fromTags(String title, String artist, String album, Integer year, Genre genre) {
    // TODO: Simplify
    return this.filter(song ->
      (title == null  || song.getTitle().equals(title))
        && (artist == null || song.getArtist().equals(artist))
        && (album == null  || song.getAlbum().equals(album))
        && (year == null   || song.getYear().equals(year))
        && (genre == null  || song.getGenre().equals(genre)));
  }

  /**
   * Filters all songs using a predicate
   * @param predicate Filter
   * @return Filtered songs
   */
  public List<Song> filter(Predicate<Song> predicate) {
    return songs.stream()
      .filter(predicate).collect(Collectors.toList());
  }

  /**
   * Removes a song object from the library
   * @param song Song that should be removed
   * @return The removed song or null if it wasn't found in the library
   */
  public Song remove(Song song) {
    return (this.songs.remove(song)) ? song : null;
  }

  /**
   * Removes all songs where title and artist match to given
   * @param title  Title
   * @param artist Artist
   * @param album  Album
   * @param year   Year
   * @param genre  Genre
   * @return List of removed songs
   * @see #fromTags
   */
  public List<Song> remove(String title, String artist, String album, Integer year, Genre genre) {
    return this.remove(this.fromTags(title, artist, album, year, genre));
  }

  /**
   * Removes a list of songs
   * @param list List of songs that should be removed
   * @return The songs the library contained
   * @see #overlap
   */
  public List<Song> remove(List<Song> list) {
    List<Song> overlap = this.overlap(list);
    this.songs.removeAll(overlap);
    return overlap;
  }

  /**
   * Removes all songs that match the predicate
   * @param predicate Filter
   * @return Removed songs
   * @see #filter
   */
  public List<Song> remove(Predicate<Song> predicate) {
    List<Song> filtered = this.filter(predicate);
    this.songs.removeAll(filtered);
    return filtered;
  }

  /**
   * Returns the first result that matches the predicate
   * @param predicate Filter
   * @return First song that matches
   * @see #filter
   */
  public Song filterOne(Predicate<Song> predicate) {
    return this.filter(predicate).get(0);
  }

  /**
   * Alters the library's limit of songs
   * @param limit New maximum number of songs that the library can have
   */
  public void setLimit(int limit) {
    this.limit = limit;
    if (this.songs.size() > 0) this.songs = this.songs.subList(0, limit - 1);
  }

  /**
   * Sorts the library based on a comparator. Static comparators can be found in the Song class,
   * e.g. Song.COMPARE_BY_TITLE, Song.COMPARE_BY_YEAR
   * @param comparator Comparator used to sort the library
   * @param descending True if order should be reversed
   */
  public void sort(Comparator<Song> comparator, boolean descending) {
    this.songs.sort(comparator);
    if (descending) this.reverse();
  }

  /**
   * Sorts ascending
   * @param comparator Comparator used to sort the library
   * @see #sort(Comparator, boolean)
   */
  public void sort(Comparator<Song> comparator) {
    this.sort(comparator, false);
  }

  /**
   * Reverses the order of all songs the library has
   */
  public void reverse() {
    Collections.reverse(this.songs);
  }

  @Override
  public String toString() {
    String string = "";
    string += this.songs.size() + " Songs\n";
    for (Song song : this.songs) string += "- " + song + "\n";
    return string;  }

  public void print() {
    System.out.println(this.toString());
  }

  public int count() {
    return this.songs.size();
  }
}
