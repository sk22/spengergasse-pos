package at.spengergasse.kai17521.gra02.music;

import at.spengergasse.kai17521.gra02.id3.Id3Version;
import at.spengergasse.kai17521.gra02.id3.InvalidId3TagException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Samuel Kaiser
 * @since 10/10/2016
 */
public class SongSetTest {
  private SongSet set;

  @Before
  public void setUp() {
    set = new SongSet();
  }

  @Test
  public void added() throws IOException, InvalidId3TagException {
    set.add(new Song(new File("Jim Yosef - Eclipse.mp3")));
    Collection<Song> songs = set.filter(
      s -> s.getTag(Id3Version.V1).get("TITLE").getValue().equals("Eclipse")
    );
    assertEquals(1, songs.size());

    Song song = set.filterOne(
      s -> s.getTag(Id3Version.V1).get("TITLE").getValue().equals("Eclipse")
    );
    assertEquals("Jim Yosef",
      song.getTag(Id3Version.V1).get("ARTIST").getValue());
  }

  @Test
  public void onlyOnce() throws IOException, InvalidId3TagException {
    set.add(new Song(new File("Waysons - Daydream.mp3")));
    set.add(new Song(new File("Waysons - Daydream.mp3")));
    assertEquals(1, set.size());
  }
}
