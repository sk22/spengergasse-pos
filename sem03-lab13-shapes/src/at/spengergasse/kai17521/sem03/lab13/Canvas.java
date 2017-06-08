package at.spengergasse.kai17521.sem03.lab13;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 1/27/2017
 */
public class Canvas extends ArrayList<Shape> {
  public void write(File file) throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    oos.writeObject(this);
  }

  @SuppressWarnings("unchecked")
  public int read(File file) throws IOException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    List<Shape> newItems;
    try {
      newItems = (List<Shape>) ois.readObject();
    } catch (Exception e) {
      throw new IOException("File could not be read", e);
    }
    this.addAll(newItems);
    return newItems.size();
  }

  public static Comparator<? super Shape> sortBy(Object area) {
    return (a, b) -> (int) Math.round(a.area() - b.area());
  }
}
