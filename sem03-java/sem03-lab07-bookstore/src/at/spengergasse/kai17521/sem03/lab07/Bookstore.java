package at.spengergasse.kai17521.sem03.lab07;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 11/21/2016
 */
public class Bookstore {
  private List<Book> books = new ArrayList<>();
  static Logger logger;

  static {
    File file = new File("error.log");
    try {
      logger = new Logger(new PrintStream(new FileOutputStream(file)));
    } catch (FileNotFoundException e) {
      logger = new Logger(System.err);
      logger.log("Cannot access error.log");
    }
  }

  public void load(String fileName) {
    try {
      BufferedReader reader =
        new BufferedReader(new FileReader(new File(fileName)));
      reader.lines().forEach(line -> books.add(Book.parseBook(line)));
    } catch (IOException ioe) {
      logger.log(ioe.toString());
    }
  }

  public void save(String fileName) {
    try {
      BufferedWriter writer =
        new BufferedWriter(new FileWriter(new File(fileName)));
      writer.write(toString());

      writer.flush();
    } catch (IOException ioe) {
      logger.log(ioe.toString());
    }
  }

  public boolean remove(Object o) {
    return books.remove(o);
  }

  public Book get(int index) {
    return books.get(index);
  }

  public void add(Book element) {
    books.add(element);
  }

  public Book remove(int index) {
    return books.remove(index);
  }

  public void print() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    return books.stream()
      .map(Book::toString)
      .reduce("", (s1, s2) -> s1 + s2 + "\n");
  }

  static class Logger {
    private PrintStream printStream;

    public Logger(PrintStream printStream) {
      this.printStream = printStream;
    }

    public void log(String message) {
      printStream.println(
        LocalDateTime.now().format(
          DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ) + " " + message
      );
    }
  }
}
