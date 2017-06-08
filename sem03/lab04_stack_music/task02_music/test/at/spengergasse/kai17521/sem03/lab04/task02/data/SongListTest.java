package at.spengergasse.kai17521.sem03.lab04.task02.data;

import at.spengergasse.kai17521.sem03.lab04.task02.data.Song;
import at.spengergasse.kai17521.sem03.lab04.task02.data.SongList;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 09/19/2016
 */
public class SongListTest {
  private static SongList lib;

  @Before
  public void init() {
    System.out.println();
    lib = new SongList(10);
    lib.addAll(
      new Song.Builder().setTitle("Smells Like Teen Spirit").setArtist("Nirvana").setYear(1991).build(),
      new Song.Builder().setTitle("Imagine").setArtist("John Lennon").setYear(1971).build(),
      new Song.Builder().setTitle("One").setArtist("U2").setYear(1991).build(),
      new Song.Builder().setTitle("Billie Jean").setArtist("Michael Jackson").setYear(1982).build(),
      new Song.Builder().setTitle("Bohemian Rhapsody").setArtist("Queen").setYear(1975).build()
    );
  }

  @After
  public void after() {
    System.out.println(lib);
  }

  @Test
  public void remove() {
    System.out.println("Removing One");

    System.out.println(lib.remove("One", "U2", null, null, null));
    System.out.println(lib.removeIf(song -> song.getYear().equals(1991)));
  }

  @Test
  public void sortTitle() {
    System.out.println("Sorting by title");
    lib.sort(Song.COMPARE_BY_TITLE);
  }

  @Test
  public void sortYear() {
    System.out.println("Sorting by year");
    lib.sort(Song.COMPARE_BY_YEAR);
  }

  @Test
  public void reverse() {
    System.out.println("Reversing the library's order");
    System.out.println(lib);
    lib.reverse();
  }

  @Test
  public void overflow() {
    System.out.println("Overflowing the library (Current number of elements: " + lib.count() + ")");
    Song[] songs = { new Song(), new Song(), new Song(), new Song(), new Song(), new Song(), new Song() };
    System.out.println("Trying to add: " + songs.length + " " + Arrays.toString(songs));
    List<Song> overflow = lib.addAll(songs);
    System.out.println("Added: " + overflow.size() + " " + overflow);
  }

  @Test
  public void count() {
    System.out.println("Counting all entries: " + lib.count());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNull() {
    System.out.println("Adding (SongList.add) one empty Song, then one null.");
    System.out.println("Expecting an IllegalArgumentException to be thrown.");
    lib.add(new Song());
    lib.add(null);
  }

  @Test
  public void addAllNull() {
    System.out.println("Adding (SongList.addAll) two empty Songs and one null");
    System.out.println("Expecting the two Songs to be added and the null to be ignored.");
    int before = lib.count();
    lib.addAll(new Song(), null, new Song());
    int after = lib.count();
    assert(before == after - 2);
  }
}
