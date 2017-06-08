package data;

import java.util.*;

/**
 * Song containing its ID3 tags
 * @author Samuel Kaiser
 * @since 09/19/2016
 */
public class Song {
  // TODO: Support all ID3-Tags (placed from song file directly into attribute (private ID3 tags)
  private String tag;
  private String title;
  private String artist;
  private String album;
  private Integer year;
  private Genre genre;

  public Song() {
    this.setTag(false);
  }

  public Song(String title, String artist, String album, Integer year, Genre genre) {
    this.setTitle(title);
    this.setArtist(artist);
    this.setAlbum(album);
    this.setYear(year);
    this.setGenre(genre);
    this.setTag(this.title != null);
  }

  private void setTag(boolean tagExists) {
    this.tag = tagExists ? "TAG" : null;
  }

  public void setTitle(String title) {
    if (title != null && title.isEmpty()) {
      throw new IllegalArgumentException("The title must not be empty.");
    }
    this.title = title;
  }

  public void setArtist(String artist) {
    if (artist != null && artist.isEmpty()) {
      throw new IllegalArgumentException("The artist must not be empty.");
    }
    this.artist = artist;
  }

  public void setAlbum(String album) {
    if (album != null && album.isEmpty()) {
      throw new IllegalArgumentException("The album must not be empty.");
    }
    this.album = album;
  }

  public void setYear(Integer year) {
    // I don't like that feature.
    /* if (year != null && LocalDate.now().getYear() < year) {
      throw new IllegalArgumentException("Year must not be in the future.");
    } */
    this.year = year;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public String getTag() {
    return tag;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getAlbum() {
    return album;
  }

  public Integer getYear() {
    return year;
  }

  public Genre getGenre() {
    return genre;
  }

  public String toStringTags() {
    List<String> list = new ArrayList<>();
    if (this.tag == null) return "Unknown";

    if (this.title  != null) list.add("title='"  + title + '\'');
    if (this.artist != null) list.add("artist='" + artist + '\'');
    if (this.album  != null) list.add("album='"  + album + '\'');
    if (this.year   != null) list.add("year="       + year);
    if (this.genre  != null) list.add("genre="   + genre);

    return list.toString();
  }

  @Override
  public String toString() {
    if (this.tag == null) return "Unknown";

    List<String> list = new ArrayList<>();
    list.add(this.getTitle());
    list.add(this.getArtist());
    list.add(this.getAlbum());

    list.removeAll(Collections.<String>singleton(null));

    String string = String.join(" - ", list);
    if (this.getYear() != null) string += " (" + year + ")";
    return string;
  }

  public static final Comparator<Song> COMPARE_BY_TITLE = (Song song1, Song song2)
    -> song1.getTitle().compareTo(song2.getTitle());

  public static final Comparator<Song> COMPARE_BY_YEAR = (Song song1, Song song2)
    -> Integer.compare(song1.getYear(), song2.getYear());

  public static class Builder {
    private String title;
    private String artist;
    private String album;
    private Integer year;
    private Genre genre;

    public Song build() {
      return new Song(this.title, this.artist, this.album, this.year, this.genre);
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setArtist(String artist) {
      this.artist = artist;
      return this;
    }

    public Builder setAlbum(String album) {
      this.album = album;
      return this;
    }

    public Builder setYear(Integer year) {
      this.year = year;
      return this;
    }

    public Builder setGenre(Genre genre) {
      this.genre = genre;
      return this;
    }
  }
}
