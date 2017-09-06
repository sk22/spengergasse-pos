import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
public abstract class Artwork implements Serializable {
  private String title;
  private String artist;
  private LocalDate date;

  public static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("dd MMM yyyy");

  /**
   * Constructs an Artwork
   * @param title  Artwork's title
   * @param artist Artist's name
   * @param date   Date of Creation
   */
  public Artwork(String title, String artist, LocalDate date) {
    setTitle(title);
    setArtist(artist);
    setDate(date);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title must not be null or empty");
    };
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    if (artist == null || artist.isEmpty()) {
      throw new IllegalArgumentException("Artist must not be null or empty");
    };
    this.artist = artist;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("Date must not be null");
    }
    this.date = date;
  }

  abstract float transportCosts();

  abstract String getAttributesRepresentation();

  @Override
  public String toString() {
    return String.format("%s: %s by %s, created on %s with costs: %s, %s",
      this.getClass().getSimpleName(), title, artist, date.format(FORMATTER),
      transportCosts(), getAttributesRepresentation());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Artwork artwork = (Artwork) o;

    return title.equals(artwork.title)
      && artist.equals(artwork.artist)
      && date.equals(artwork.date);

  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + artist.hashCode();
    result = 31 * result + date.hashCode();
    return result;
  }
}
