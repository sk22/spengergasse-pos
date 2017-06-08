package test;

import data.Song;
import org.junit.Test;

// import java.time.LocalDate;

/**
 * @author Samuel Kaiser
 * @since 09/22/2016
 */
public class SongTest {
  @Test
  public void toStringTest() throws Exception {
    Song song = new Song.Builder().setTitle("Tremor").setArtist("Dimitri Vegas, Martin Garrix, Like Mike").build();
    System.out.println(song);
  }

  @Test
  public void toStringTags() throws Exception {
    Song song = new Song.Builder().setTitle("Tremor").setArtist("Dimitri Vegas, Martin Garrix, Like Mike").build();
    System.out.println(song.toStringTags());
  }

  // excluded. see Song.java:58
  /* @Test(expected = IllegalArgumentException.class)
  public void invalidYear() {
    int currentYear = LocalDate.now().getYear();
    System.out.println("Trying to build Song with year " + (currentYear + 1));
    new Song.Builder().setTitle("Some song that does not exist yet").setYear(currentYear + 1).build();
  } */
}
