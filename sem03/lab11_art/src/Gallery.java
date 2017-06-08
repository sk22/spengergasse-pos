import java.io.*;
import java.util.*;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
@SuppressWarnings("ALL")
public class Gallery implements Serializable {
  private List<Artwork> artworks = new ArrayList<>();

  public static final Comparator<Artwork> BY_ARTIST =
    (a1, a2) -> a1.getArtist().compareTo(a2.getArtist());

  public static final Comparator<Artwork> BY_DATE =
    (a1, a2) -> a1.getDate().compareTo(a2.getDate());

  public static final Comparator<Artwork> BY_TRANSPORT_COSTS =
    (a1, a2) -> Float.compare(a1.transportCosts(), a2.transportCosts());

  public static final File STORAGE_FILE = new File("artworks.ser");

  public Gallery() {}

  public Gallery(Collection<Artwork> artworks) {
    artworks.forEach(this::buyArtwork);
  }

  public List<Artwork> getArtworks() {
    return new ArrayList<>(artworks);
  }

  public Artwork buyArtwork(Artwork artwork) {
    return this.artworks.contains(artwork) || !this.artworks.add(artwork)
      ? null : artwork;
  }

  public boolean sell(Artwork artwork) {
    return this.artworks.remove(artwork);
  }

  public boolean addAll(Collection<? extends Artwork> c) {
    return artworks.addAll(c);
  }

  public void sort(Comparator<Artwork> comparator) {
    artworks.sort(comparator);
  }

  public void save(File file) throws IOException {
    ObjectOutput objectOut =
      new ObjectOutputStream(new FileOutputStream(file));

    objectOut.writeObject(artworks);
  }

  public void load(File file) throws IOException, ClassNotFoundException {
    ObjectInput objectIn =
      new ObjectInputStream(new FileInputStream(file));

    artworks.addAll((List<Artwork>) objectIn.readObject());
  }

  public void storeArtworks() throws IOException, ClassNotFoundException {
    save(STORAGE_FILE);
  }

  public void restoreArtworks() throws IOException, ClassNotFoundException {
    load(STORAGE_FILE);
  }

  public int size() {
    return artworks.size();
  }

  @Override
  public String toString() {
    return artworks.stream()
      .map(Artwork::toString)
      .reduce("", (prev, curr) -> prev + curr + "\n");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Gallery gallery = (Gallery) o;

    if (artworks != null
      ? !artworks.equals(gallery.artworks)
      : gallery.artworks != null) {
      return false;
    }

    else return true;
  }

  @Override
  public int hashCode() {
    return artworks != null ? artworks.hashCode() : 0;
  }
}
