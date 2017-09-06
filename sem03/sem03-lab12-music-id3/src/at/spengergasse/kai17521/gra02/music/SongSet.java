package at.spengergasse.kai17521.gra02.music;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 10/10/2016
 */
public class SongSet extends HashSet<Song> implements SongCollection {
  @Override
  public Collection<Song> overlap(Collection<Song> songs) {
    Set<Song> result = new HashSet<>();
    songs.forEach(song -> { if (contains(songs)) result.add(song); });
    return result;
  }

  @Override
  public Collection<Song> filter(Predicate<Song> predicate) {
    return stream().filter(predicate).collect(Collectors.toList());
  }

  @Override
  public Collection<Song> remove(Collection<Song> songs) {
    Collection<Song> overlap = overlap(songs);
    removeAll(overlap);
    return overlap;
  }

  @Override
  public Song filterOne(Predicate<Song> predicate) {
    return stream().filter(predicate).findAny().orElse(null);
  }

  @Override
  public String toString() {
    return size() + " Songs\n" + stream()
      .map(Song::toString)
      .reduce("", (prev, curr) -> prev + "- " + curr + "\n");
  }
}
