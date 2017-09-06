package at.spengergasse.kai17521.gra02.id3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

/**
 * @author Samuel Kaiser
 * @since 1/16/2017
 */
public class Id3V1ParserTest {
  private Id3Tag tag;

  @Before
  public void parse() throws IOException, InvalidId3TagException {
    tag = Id3Tag.parse(Id3Version.V1, new Id3Reader.V1(
      new File("Groove Coverage - You (Danceboy Remix).mp3")
    ).read());
  }

  @Test
  public void parsed() {
    assertEquals("Groove Coverage", tag.get("ARTIST").getValue());
  }

  @Test
  public void print() {
    System.out.println(tag);
  }
}
