package at.spengergasse.kai17521.sem04.lab19.model;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Samuel Kaiser
 * @since 3/13/2017
 */
public class ShapeList extends ArrayList<DrawableShape> {
  private transient File file;

  public void saveTo(File file) throws Exception {
    this.file = file;
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    oos.writeObject(this);
  }

  public boolean save() throws Exception {
    if (file == null) return false;
    saveTo(file);
    return true;
  }

  public void open(File file) throws Exception {
    this.file = file;
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    Object readObject = ois.readObject();
    if (!(readObject instanceof ShapeList)) return;
    ShapeList newList = (ShapeList) readObject;
    clear();
    addAll(newList);
  }
}
