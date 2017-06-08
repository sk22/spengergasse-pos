package at.spengergasse.kai17521.sem03.lab07;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 11/21/2016
 */
public class BookstoreTest {
  @Test
  public void load() {
    Bookstore store = new Bookstore();
    store.load("books.txt");
    store.print();
  }

  @Test
  public void save() {
    Bookstore store = new Bookstore();
    store.add(new Book("978-3-8362-1802-3", "Java ist auch eine Insel",
      "Christian Ullenboom", "Rheinwerk Computing"));
    store.add(new Book("978-0-385-35139-3", "The Circle",
      "David Eggers", "McSweeney's"));
    store.save("books.txt");
  }

  @Test
  public void loadInvalid() {
    new Bookstore().load("doesnotexist.txt");
    assertTrue("error.log was created", new File("error.log").exists());
  }

  @Test
  public void saveInvalid() {
    new Bookstore().save("/someinvaliddir/file.txt");
    assertTrue("error.log was created", new File("error.log").exists());
  }
}