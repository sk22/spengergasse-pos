package at.spengergasse.kai17521.sem03.lab07;

import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 11/21/2016
 */
public class Book {
  private String isbn;
  private String title;
  private List<String> authors;
  private String publisher;

  public Book(String isbn, String title, String author, String publisher) {
    this(isbn, title, Collections.singletonList(author), publisher);
  }

  public Book(String isbn, String title,
              List<String> authors, String publisher) {
    setIsbn(isbn);
    setTitle(title);
    setAuthors(authors);
    setPublisher(publisher);
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    if (isbn == null || isbn.length() <= 0) {
      throw new IllegalArgumentException("Invalid ISBN");
    }
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    if (title == null || title.length() <= 0) {
      throw new IllegalArgumentException("Invalid title");
    }
    this.title = title;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public static Book parseBook(String representation)
    throws IllegalArgumentException {
    String[] params = representation.split("\\s*;\\s*");
    if (params.length != 4) return null;
    return new Book(params[0], params[1], parseAuthors(params[2]), params[3]);
  }

  public static List<String> parseAuthors(String representation) {
    return Arrays.asList(representation.split("\\s*:\\s*"));
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  @Override
  public String toString() {
    return isbn + "; " + title + "; " +
      String.join(":", authors) + "; " + publisher;
  }
}