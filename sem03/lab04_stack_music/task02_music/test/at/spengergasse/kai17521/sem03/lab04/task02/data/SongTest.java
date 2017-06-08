package at.spengergasse.kai17521.sem03.lab04.task02.data;

import at.spengergasse.kai17521.sem03.lab04.task02.data.Song;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Samuel Kaiser
 * @since 09/22/2016
 */
public class SongTest {
  private Song song;

  @Before
  public void makeSong() throws Exception {
    this.song = new Song.Builder().setTitle("Tremor").setArtist("Dimitri Vegas").build();
  }

  @Test
  public void toStringTest() throws Exception {
    System.out.println(song);
  }

  @Test
  public void toStringTags() throws Exception {
    System.out.println(song.toStringTags());
  }

  @Test
  public void equalsSong() throws Exception {
    System.out.println("Song.equals checks Title and Artist");
    Song comparison;
    comparison = new Song.Builder().setTitle("Tremor").setArtist("Dimitri Vegas").build();
    assert song.equals(comparison);
    comparison = new Song.Builder().setTitle("Tremör").setArtist("Dimitri Vegas").build();
    assert !song.equals(comparison);

    System.out.println("Does not equal a Song without the needed ID3 tags");
    assert !song.equals(new Song());
  }

  // excluded. see Song.java:58
  /* @Test(expected = IllegalArgumentException.class)
  public void invalidYear() {
    int currentYear = LocalDate.now().getYear();
    System.out.println("Trying to build Song with year " + (currentYear + 1));
    new Song.Builder().setTitle("Some song that does not exist yet").setYear(currentYear + 1).build();
  } */
}
