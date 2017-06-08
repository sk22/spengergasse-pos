package at.spengergasse.kai17521.sem03.lab04.task02.data;

import org.junit.Test;

/**
 * @author Samuel Kaiser
 * @since 10/10/2016
 */
public class LibraryTest {
  Library lib;

  @Test
  public void add() throws Exception {
    lib = new Library();
    lib.add(new Song.Builder().setTitle("Highway To Hell").setArtist("AC/DC").build());
    assert lib.get(0).getTitle().equals("Highway To Hell");

    System.out.println("Songs with same title and artist can only be added once");
    Song another = new Song.Builder().setTitle("Highway to hell").setArtist("Ac/Dc").build();
    assert lib.add(another); // returns true
    assert lib.size() == 1;
  }

  @Test
  public void addAll() throws Exception {

  }
}