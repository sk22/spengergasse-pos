package at.spengergasse.kai17521.sem04.lab21.model;

import at.spengergasse.kai17521.id3.Id3Reader;
import at.spengergasse.kai17521.id3.Id3Tag;
import at.spengergasse.kai17521.id3.Id3Version;
import at.spengergasse.kai17521.id3.InvalidId3TagException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Song containing the file and ID3 tag
 * @author Samuel Kaiser
 * @since 09/19/2016
 */
public class Song implements Serializable {
  private File file;
  private Map<Id3Version, Id3Tag> tags = new HashMap<>();

  public Song(File file) throws IOException {
    if (file == null) {
      throw new IllegalArgumentException("File must not be null");
    }
    this.file = file;
    readId3();
  }

  public void readId3() {
    try {
      tags.put(Id3Version.V1, Id3Tag.parse(Id3Version.V1,
        new Id3Reader.V1(file).read()));
    } catch (InvalidId3TagException e) {
      System.err.println("ID3v1 tag could not be parsed");
    } catch (IOException e) {
      System.err.println("ID3v1 tag could not be parsed due to an IOException");
    }
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public Id3Tag getTag(Id3Version v) {
    return tags.get(v);
  }

  public void setTag(Id3Version version, Id3Tag tag) {
    tags.put(version, tag);
  }

  public Map<Id3Version, Id3Tag> getTags() {
    return tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Song song = (Song) o;

    return file.equals(song.file);

  }

  @Override
  public int hashCode() {
    return file.hashCode();
  }

  @Override
  public String toString() {
    Id3Tag tag = getTag(Id3Version.V1);
    Object title = tag.get("TITLE").getValue();
    if (title == null || title.toString().length() == 0) return file.getName();
    return tag.get("TITLE").getValue() + " - " + tag.get("ARTIST").getValue();
  }
}
