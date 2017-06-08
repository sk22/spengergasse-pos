package at.spengergasse.kai17521.sem04.the20160310.data;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class Management extends ArrayList<Contact> implements Serializable {
  public void save() throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(
      new FileOutputStream("contacts.ser")
    );
    oos.writeObject(this);
  }

  public void load() throws IOException {
    try {
      ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("contacts.ser")
      );
      // noinspection unchecked
      Management loaded = (Management) ois.readObject();
      addAll(loaded);
    } catch (ClassNotFoundException ignored) {}
  }

  @Override
  public String toString() {
    return stream()
      .map(Contact::toString)
      .collect(Collectors.joining("\n"));
  }
}
