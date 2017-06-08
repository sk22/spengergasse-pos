package at.spengergasse.kai17521.sem03.lab07;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 11/21/2016
 */
public class BookTest {
  @Test
  public void parseAuthors() {
    String representation = "Christian Ullenboom : Christian Ullenbutz";
    List<String> authors = Book.parseAuthors(representation);
    assertEquals("Christian Ullenboom", authors.get(0));
    assertEquals("Christian Ullenbutz", authors.get(1));
  }

  @Test
  public void parseBook() {
    String representation = "978-3-8362-1802-3; " +
      "Java ist auch eine Insel; Christian Ullenboom; Rheinwerk Computing";
    Book book = Book.parseBook(representation);
    assertEquals("Java ist auch eine Insel", book.getTitle());
    assertEquals("Christian Ullenboom", book.getAuthors().get(0));
    assertEquals(1, book.getAuthors().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidValues() {
    new Book("", "", "", "");
  }
}