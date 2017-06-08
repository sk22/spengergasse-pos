import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
public class GalleryTest {
  private Gallery gallery;

  @Before
  public void init() {
    gallery = new Gallery(Arrays.asList(
      new Painting("Boo", "Far", LocalDate.of(1999, 12, 12),
        new Painting.Dimensions(300, 300), Painting.Technique.ACRYL),
      new Painting("Buz", "Quuz", LocalDate.of(1820, 12, 12),
        new Painting.Dimensions(300, 400), Painting.Technique.OIL)
    ));
  }

  @Test
  public void sell() {
    int before = gallery.size();
    assertTrue(
      gallery.sell(new Painting("Boo", "Far",LocalDate.of(1999, 12, 12),
        new Painting.Dimensions(300, 300), Painting.Technique.ACRYL))
    );
    assertFalse(
      gallery.sell(new Painting("Not added", "Yet", LocalDate.now(),
        new Painting.Dimensions(10, 10), Painting.Technique.ACRYL))
    );
    assertEquals(before - 1, gallery.size());
  }

  @Test
  public void io() throws IOException, ClassNotFoundException {
    int before = gallery.size();
    Gallery ser = new Gallery(Arrays.asList(
      new Sculpture("Sculpture", "Artist", LocalDate.now(),
        Sculpture.Material.PLASTER, 10, 10),
      new Painting("Painting", "Artist", LocalDate.now(),
        new Painting.Dimensions(123, 321), Painting.Technique.OIL)
    ));
    ser.storeArtworks();
    gallery.restoreArtworks();

    assertEquals(before + ser.size(), gallery.size());
  }

  @Test
  public void sort() {
    System.out.println("By Artist");
    gallery.sort(Gallery.BY_ARTIST);
    System.out.println(gallery);

    System.out.println("By Date");
    gallery.sort(Gallery.BY_DATE);
    System.out.println(gallery);

    System.out.println("By Transport Costs");
    gallery.sort(Gallery.BY_TRANSPORT_COSTS);
    System.out.println(gallery);
  }

  @Test
  public void toStringTest() {
    System.out.println(gallery);
  }

}