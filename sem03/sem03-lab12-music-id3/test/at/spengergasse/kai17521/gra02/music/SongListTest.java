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
public class SongListTest {
  private SongList list;

  @Before
  public void listUp() {
    list = new SongList();
  }

  @Test
  public void added() throws IOException, InvalidId3TagException {
    list.add(new Song(new File("Waysons - Daydream.mp3")));
    Collection<Song> songs = list.filter(
      s -> s.getTag(Id3Version.V1).get("TITLE").getValue().equals("Daydream")
    );
    assertEquals(1, songs.size());

    Song song = list.filterOne(
      s -> s.getTag(Id3Version.V1).get("TITLE").getValue().equals("Daydream")
    );
    assertEquals("Waysons",
      song.getTag(Id3Version.V1).get("ARTIST").getValue());
  }

  @Test
  public void multiple() throws IOException,InvalidId3TagException {
    list.add(new Song(new File("Waysons - Daydream.mp3")));
    list.add(new Song(new File("Waysons - Daydream.mp3")));
    assertEquals(2, list.size());
  }
}
